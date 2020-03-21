package com.DTeam.eshop.utilities.pdfgenerator;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.DTeam.eshop.entities.Payment;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class EaringsReportPDF {
    public static ByteArrayInputStream earningsReport(List<Payment> paymentList) throws IOException {

		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {
            Integer i = 1;
            Double kwota = 0.0;
			PdfPTable table = new PdfPTable(4);
			table.setWidthPercentage(80);
            table.setWidths(new int[] { 2, 4, 4, 4});

            BaseFont helvetica = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED);
            Font headFont = new Font(helvetica, 12, Font.NORMAL);

			PdfPCell hcell;
			hcell = new PdfPCell(new Phrase("Id", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Data płatności", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Metoda płatności", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Kwota", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			for (Payment payment : paymentList) {
				PdfPCell cell;
                cell = new PdfPCell(new Phrase(i.toString(),headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

                LocalDateTime dateTime = LocalDateTime.parse(payment.getPaymentDate(),DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                DateTimeFormatter patern = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formattedDate = dateTime.format(patern);
				cell = new PdfPCell(new Phrase(formattedDate,headFont));
				cell.setPaddingLeft(5);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(payment.getPaymentMethod(),headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingRight(5);
                table.addCell(cell);

                String amount = String.format("%.2f", payment.getAmount());
				cell = new PdfPCell(new Phrase(amount + " zł",headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

                i++;
                kwota = kwota + payment.getAmount();
			}

			PdfWriter.getInstance(document, out);
            document.open();
            Paragraph header = new Paragraph("Miesięczny raport zysków",headFont);
            header.setAlignment(Element.ALIGN_CENTER);
            document.add(header);
            document.add(Chunk.NEWLINE);
            document.add(table);
            document.add(Chunk.NEWLINE);
            Paragraph footer = new Paragraph("Suma transakcji: " + String.format("%.2f", kwota) + " zł", headFont);
            footer.setAlignment(Element.ALIGN_RIGHT);
			document.add(footer);

			LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter patern = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String formattedDate = now.format(patern);

            document.add(Chunk.NEWLINE);
            Paragraph date = new Paragraph("Wygenerowano: " + formattedDate, headFont);
            date.setAlignment(Element.ALIGN_CENTER);
            document.add(date);
			document.close();

		} catch (DocumentException ex) {
            ex.printStackTrace();
		}
		return new ByteArrayInputStream(out.toByteArray());
	}
}