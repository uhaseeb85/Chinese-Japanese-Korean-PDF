/**
 * 
 */
package com.file.pdf;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lr.bos.bo.FetchPurchaseOrderBO;

/**
 * @author uhase
 *
 */
public class MainMethod {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AuditsPoQuote auditsPoQuote = new AuditsPoQuote();
		AuditsPoTableData auditsPoTableData = new AuditsPoTableData();
		List<String> headersList = new ArrayList<>();
		headersList.add(new String("家".getBytes(), Charset.forName("UTF-8")));
		headersList.add(new String("안녕하세요".getBytes(), Charset.forName("UTF-8")));
		headersList.add(new String("안녕하세요".getBytes(), Charset.forName("UTF-8")));
		headersList.add("안녕하세요");
		headersList.add("家");
		headersList.add("家");
		headersList.add("家");
		headersList.add("English");
		headersList.add("こんにちは");
		headersList.add("こんにちは");
		headersList.add("家");

		Map<Integer, List<FetchPurchaseOrderBO>> dataMap = new HashMap<>();
		List<FetchPurchaseOrderBO> fetchPurchaseOrderBOList = new ArrayList<FetchPurchaseOrderBO>();
		FetchPurchaseOrderBO fetchPurchaseOrderBO = new FetchPurchaseOrderBO();
		fetchPurchaseOrderBO.setActivityType("some");
		fetchPurchaseOrderBO.setAddressDescription("some");
		fetchPurchaseOrderBO.setAddressLine1("some");
		fetchPurchaseOrderBO.setAddressLine2("some");
		fetchPurchaseOrderBO.setAssessorName("some");
		fetchPurchaseOrderBO.setBillingCurrency("some");
		fetchPurchaseOrderBO.setCity("some");
		fetchPurchaseOrderBO.setClientName("some");
		fetchPurchaseOrderBO.setCountry("some");
		fetchPurchaseOrderBO.setCurrency("some");
		fetchPurchaseOrderBO.setLrReference("some");
		fetchPurchaseOrderBO.setNetPrice("some");
		fetchPurchaseOrderBO.setNetPriceCurrency("some");
		fetchPurchaseOrderBO.setNetValue("some");
		fetchPurchaseOrderBO.setPartySiteId("some");
		fetchPurchaseOrderBO.setNetValueCurrency("some");
		fetchPurchaseOrderBO.setPostalCode("some");
		fetchPurchaseOrderBO.setProduct("some");
		fetchPurchaseOrderBO.setQuantity("some");
		fetchPurchaseOrderBO.setSiteAddress("some");
		fetchPurchaseOrderBO.setTaxRate("some");
		fetchPurchaseOrderBO.setTotal("some");
		fetchPurchaseOrderBO.setTotalCurrency("some");
		fetchPurchaseOrderBO.setTotalItemNetValue("some");
		fetchPurchaseOrderBO.setTotalItemNetValueCurrency("some");
		fetchPurchaseOrderBO.setVisitDate("some");
		fetchPurchaseOrderBO.setValueAddedTaxCurrency("some");
		fetchPurchaseOrderBO.setValueAddedTax("some");
		fetchPurchaseOrderBOList.add(fetchPurchaseOrderBO);
		dataMap.put(1, fetchPurchaseOrderBOList);

		auditsPoTableData.setBillToAddr("some");
		auditsPoTableData.setHeadersList(headersList);
		auditsPoTableData.setDataMap(dataMap);
		auditsPoTableData.setBillToAddr("some");
		auditsPoTableData.setPoQuoted("some");
		auditsPoTableData.setPoTerms("some");
		auditsPoTableData.setTaxlabel("some");
		auditsPoTableData.setTitle("some");
		auditsPoTableData.setTotal("some");
		auditsPoTableData.setTotalItemLabel("some");
		auditsPoTableData.setTotalItemNetValue("some");
		auditsPoTableData.setTotalLabel("some");
		auditsPoTableData.setValueAddedTax("some");
		try {
			Path path = Paths.get("C:\\Coding\\Eclipse Workspace\\Test\\resources\\result.pdf");
			Files.write(path, auditsPoQuote.createPdf(auditsPoTableData));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
