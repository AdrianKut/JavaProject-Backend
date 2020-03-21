package com.DTeam.eshop.utilities.pdfgenerator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.DTeam.eshop.entities.Order;
import com.DTeam.eshop.entities.Product;
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

public class OrderReportPDF {

    public static ByteArrayInputStream order(List<Order> orderList) throws IOException {

		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {
            Integer i = 1;

			PdfPTable table = new PdfPTable(4);
			table.setWidthPercentage(80);
            table.setWidths(new int[] { 2, 4, 4, 4});

            BaseFont helvetica = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED);
            Font headFont = new Font(helvetica, 12, Font.NORMAL);

			PdfPCell hcell;
			hcell = new PdfPCell(new Phrase("Id", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Nazwa", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Klient", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Adres", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			for (Order order : orderList) {
				PdfPCell cell;
                cell = new PdfPCell(new Phrase(i.toString(),headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

                String products= "";
                for (Product product : order.getProducts()) {
                    products = products + "\n" + product.getName();
                }
				cell = new PdfPCell(new Phrase(products,headFont));
				cell.setPaddingLeft(5);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(order.getCustomer().getName() + " " + order.getCustomer().getSurname(),headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingRight(5);
                table.addCell(cell);
                if(order.getCustomer().getAddress().getFlatNumber() == null){
                    cell = new PdfPCell(new Phrase(order.getCustomer().getAddress().getStreet() +  " " + order.getCustomer().getAddress().getHouseNumber()
                    + ", " + order.getCustomer().getAddress().getPostcode() + " " +
                    order.getCustomer().getAddress().getCity(),headFont));
                } else{
                    cell = new PdfPCell(new Phrase(order.getCustomer().getAddress().getStreet() +  " " + order.getCustomer().getAddress().getHouseNumber()
                    +  "/" + order.getCustomer().getAddress().getFlatNumber() +", " + order.getCustomer().getAddress().getPostcode() + " " +
                    order.getCustomer().getAddress().getCity(),headFont));
                }

				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

                i++;
			}

			PdfWriter.getInstance(document, out);
            document.open();
            Paragraph header = new Paragraph("Zamówienia do zrealizowania",headFont);
            header.setAlignment(Element.ALIGN_CENTER);
            document.add(header);
            document.add(Chunk.NEWLINE);
            document.add(table);
            document.add(Chunk.NEWLINE);

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