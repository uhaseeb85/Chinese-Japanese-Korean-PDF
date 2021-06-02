package com.file.pdf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lr.bos.bo.FetchPurchaseOrderBO;

/**
 * The Class CreatePdf.
 */
public class AuditsPoQuote {

	private static final int TITLE_FONT_SIZE = 12;
	private static final BaseColor TITLE_FONT_COLOUR = BaseColor.BLUE;

	private static final int COLUMN_FONT_SIZE = 8;
	private static final BaseColor COLUMN_BACKGROUND_COLOUR = BaseColor.GRAY;
	private static final BaseColor COLUMN_FONT_COLOR = BaseColor.BLACK;

	private static final int ROW_FONT_SIZE = 8;
	private static final BaseColor ROW_FONT_COLOUR = BaseColor.BLACK;

	private static final int ADDRESS_FONT_SIZE = 10;
	private static final BaseColor ADDRESS_FONT_COLOUR = BaseColor.BLACK;

	private static final int SUMMARY_FONT_SIZE = 10;
	private static final BaseColor SUMMARY_FONT_COLOUR = BaseColor.BLACK;

	private static final String PDF_AUTHOR_METADATA = "Lloyds Register";
	private static final String PDF_CREATOR_METADATA = "https://www.lr.org/en/";
	private static final String PDF_TITLE_METADATA = "Quotation";
	private static final String PDF_SUBJECT_METADATA = "Quotation Details.";

	/**
	 * Creates the PDF.
	 *
	 * @param tableData the table data
	 * @return
	 * @return the byte[]
	 * @throws Exception the exception
	 */
	public byte[] createPdf(AuditsPoTableData tableData) throws Exception {
		Document document = new Document();
		ByteArrayOutputStream baOut = new ByteArrayOutputStream();
		try {
			PdfWriter writer = PdfWriter.getInstance(document, baOut);

			document.open();

			addDocumentMetaData(document);

			addTitle(document, tableData);

			addAddressTitle(document, tableData);

			addAddress(document, tableData);

			for (Entry<Integer, List<FetchPurchaseOrderBO>> map : tableData.getDataMap().entrySet()) {
				createTable(document, tableData, map.getValue());
			}

			addTerms(document, tableData);

			document.close();
			writer.close();
			baOut.close();

		} catch (Exception e) {
			System.out.println("PDF Creation Failed. :: " + e.getMessage());
			throw e;
		}
		System.out.println("PDF Created Successfully.");
		return baOut.toByteArray();
	}

	/**
	 * Adds the quotation.
	 *
	 * @param document  the document
	 * @param tableData the table data
	 * @throws DocumentException the document exception
	 * @throws IOException 
	 */
	private static void addTitle(Document document, AuditsPoTableData tableData) throws DocumentException, IOException {

		BaseFont cjkFont = BaseFont.createFont("HYGoThic-Medium", "UniKS-UCS2-H", BaseFont.NOT_EMBEDDED);
		Font blue = new Font(cjkFont, TITLE_FONT_SIZE, Font.BOLD);
		blue.setColor(TITLE_FONT_COLOUR);
		blue.setFamily(FontFamily.TIMES_ROMAN.name());
		Chunk blueText = new Chunk(tableData.getTitle(), blue);
		Paragraph p3 = new Paragraph();
		p3.setAlignment(Element.ALIGN_CENTER);
		document.add(p3);
		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);

		Paragraph p1 = new Paragraph(blueText);
		p1.setAlignment(Element.ALIGN_CENTER);
		document.add(p1);
		Paragraph p2 = new Paragraph();
		for (Entry<Integer, List<FetchPurchaseOrderBO>> map : tableData.getDataMap().entrySet()) {
			List<FetchPurchaseOrderBO> list = map.getValue();
			for (FetchPurchaseOrderBO row : list) {
				p2.add(row.getClientName());
				break;
			}
			break;
		}
		p2.setAlignment(Element.ALIGN_CENTER);
		document.add(p2);
	}

	/**
	 * Adds the address.
	 *
	 * @param document  the document
	 * @param tableData the table data
	 * @throws DocumentException the document exception
	 * @throws IOException 
	 */
	private static void addAddress(Document document, AuditsPoTableData tableData) throws DocumentException, IOException {
		for (Entry<Integer, List<FetchPurchaseOrderBO>> map : tableData.getDataMap().entrySet()) {
			List<FetchPurchaseOrderBO> list = map.getValue();
			for (FetchPurchaseOrderBO row : list) {
				createAdddressLine(row.getAddressLine1(), document);
				createAdddressLine(row.getAddressLine2(), document);
				createAdddressLine(row.getCity(), document);
				createAdddressLine(row.getPostalCode(), document);
				createAdddressLine(row.getCountry(), document);
				break;
			}
			break;
		}
		// document.add(Chunk.NEWLINE);
		// document.add(Chunk.NEWLINE);
	}

	/**
	 * Creates the adddress line.
	 *
	 * @param content  the content
	 * @param document the document
	 * @throws DocumentException the document exception
	 * @throws IOException 
	 */
	private static void createAdddressLine(String content, Document document) throws DocumentException, IOException {

		Chunk text = new Chunk(content, createFont(ADDRESS_FONT_SIZE, ADDRESS_FONT_COLOUR));
		Paragraph p = new Paragraph(text);
		p.setAlignment(Element.ALIGN_RIGHT);
		document.add(p);
	}

	/**
	 * Creates the font.
	 *
	 * @param size  the size
	 * @param color the color
	 * @return the font
	 * @throws IOException 
	 * @throws DocumentException 
	 */
	private static Font createFont(int size, BaseColor color) throws DocumentException, IOException {
		
		BaseFont cjkFont = BaseFont.createFont("HYGoThic-Medium", "UniKS-UCS2-H", BaseFont.NOT_EMBEDDED);
		Font font = new Font(cjkFont, size, Font.NORMAL);
		font.setColor(color);
		font.setFamily(FontFamily.TIMES_ROMAN.name());
		return font;
	}

	/**
	 * Creates the table.
	 *
	 * @param document    the document
	 * @param tableData   the table data
	 * @param rowDataList the row data list
	 * @throws DocumentException the document exception
	 * @throws IOException 
	 */
	private static void createTable(Document document, AuditsPoTableData tableData,
			List<FetchPurchaseOrderBO> rowDataList) throws DocumentException, IOException {
		PdfPTable table = new PdfPTable(tableData.getHeadersList().size());
		table.setWidthPercentage(105); // Width 100%
		table.setSpacingBefore(10f); // Space before table
		table.setSpacingAfter(10f); // Space after table

		// Set Column widths
		float[] columnWidths = { 1.5f, 1.2f, 1.3f, 1.2f, 1.2f, 2f, 1.3f, 1f, 1.2f, 1f, 1.2f };
		table.setWidths(columnWidths);

		for (String tableHeader : tableData.getHeadersList()) {
			Chunk text = new Chunk(tableHeader, createFont(COLUMN_FONT_SIZE, COLUMN_FONT_COLOR));
			Paragraph p1 = new Paragraph(text);
			PdfPCell cell = new PdfPCell(p1);
			cell.setBorderColor(BaseColor.BLACK);
			cell.setPaddingLeft(5);
			cell.setPaddingBottom(5);
			cell.setPaddingTop(10);
			cell.setPaddingRight(5);
			cell.setBackgroundColor(COLUMN_BACKGROUND_COLOUR);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(cell);
		}

		for (FetchPurchaseOrderBO rowData : rowDataList) {
			createDataCell(rowData.getLrReference(), table);
			createDataCell(rowData.getActivityType(), table);
			createDataCell(rowData.getProduct(), table);
			createDataCell(rowData.getQuantity(), table);
			createDataCell(rowData.getAssessorName(), table);
			createDataCell(rowData.getSiteAddress(), table);
			try {
				DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
				String visitDate = dateFormat.format(rowData.getVisitDate());
				createDataCell(visitDate, table);
			} catch (Exception e) {

			}
			createDataCell(rowData.getNetPriceCurrency(), table);
			createDataCell(rowData.getBillingCurrency(), table);
			createDataCell(rowData.getTaxRate(), table);
			createDataCell(rowData.getNetValueCurrency(), table);

		}

		document.add(table);
		HashSet<String> currencySet = new HashSet<>();
		for (FetchPurchaseOrderBO poDetailsBO : rowDataList) {
			currencySet.add(poDetailsBO.getCurrency());
			System.out.println("Inside po Currency Set" + poDetailsBO.getCurrency());
		}
		if (currencySet.size() == 1 && !rowDataList.get(0).getTotalCurrency().contains("AT_COST")) {

			Chunk text1 = new Chunk(
					tableData.getTotalItemLabel() + "        " + rowDataList.get(0).getTotalItemNetValueCurrency(),
					createFont(SUMMARY_FONT_SIZE, SUMMARY_FONT_COLOUR));
			Paragraph netValue = new Paragraph(text1);
			netValue.setAlignment(Element.ALIGN_RIGHT);
			document.add(netValue);
			// document.add(Chunk.NEWLINE);

			Chunk text2 = new Chunk(
					tableData.getTaxlabel() + "          " + rowDataList.get(0).getValueAddedTaxCurrency(),
					createFont(SUMMARY_FONT_SIZE, SUMMARY_FONT_COLOUR));
			Paragraph tax = new Paragraph(text2);
			tax.setAlignment(Element.ALIGN_RIGHT);
			document.add(tax);
			// document.add(Chunk.NEWLINE);

			Chunk text3 = new Chunk(
					tableData.getTotalLabel() + "                         " + rowDataList.get(0).getTotalCurrency(),
					createFont(SUMMARY_FONT_SIZE, SUMMARY_FONT_COLOUR));
			Paragraph total = new Paragraph(text3);
			total.setAlignment(Element.ALIGN_RIGHT);
			document.add(total);
			document.add(Chunk.NEWLINE);

		}

		if (rowDataList.get(0).getTotalCurrency().contains("AT_COST")) {
			Chunk textCost = new Chunk("Travel costs and time will be charged according to Contract",
					createFont(COLUMN_FONT_SIZE, BaseColor.GRAY));
			Paragraph atCost = new Paragraph(textCost);
			atCost.setAlignment(Element.ALIGN_CENTER);
			document.add(atCost);
			document.add(Chunk.NEWLINE);
		}

		Chunk text4 = new Chunk(tableData.getPoQuoted(), createFont(6, BaseColor.BLUE));
		Paragraph quote = new Paragraph(text4);
		quote.setAlignment(Element.ALIGN_RIGHT);
		document.add(quote);
		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);

	}

	/**
	 * Creates the data cell.
	 *
	 * @param cellData the cell data
	 * @param table    the table
	 * @throws IOException 
	 * @throws DocumentException 
	 */
	private static void createDataCell(String cellData, PdfPTable table) throws DocumentException, IOException {
		Chunk text = new Chunk(cellData, createFont(ROW_FONT_SIZE, ROW_FONT_COLOUR));
		PdfPCell cell = new PdfPCell(new Paragraph(text));
		cell.setBorderColor(BaseColor.BLACK);
		cell.setPaddingBottom(5);
		cell.setPaddingTop(5);
		cell.setPaddingLeft(5);
		cell.setPaddingRight(5);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(cell);
	}

	/**
	 * Adds the document properties.
	 *
	 * @param document the document
	 */
	private static void addDocumentMetaData(Document document) {
		document.addAuthor(PDF_AUTHOR_METADATA);
		document.addCreationDate();
		document.addCreator(PDF_CREATOR_METADATA);
		document.addTitle(PDF_TITLE_METADATA);
		document.addSubject(PDF_SUBJECT_METADATA);
	}

	private void addTerms(Document document, AuditsPoTableData tableData) throws DocumentException, IOException {

		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);
		Chunk text5 = new Chunk(tableData.getPoTerms(), createFont(6, BaseColor.BLACK));
		Paragraph terms = new Paragraph(text5);
		terms.setAlignment(Element.ALIGN_BOTTOM);
		document.add(terms);
	}

	private void addAddressTitle(Document document, AuditsPoTableData tableData) throws DocumentException, IOException {

		Chunk text1 = new Chunk(tableData.getBillToAddr(), createFont(10, BaseColor.BLACK));
		// text1.setUnderline(0.1f, -2f);
		Paragraph addr = new Paragraph(text1);
		addr.setAlignment(Element.ALIGN_RIGHT);
		document.add(addr);
	}
}
