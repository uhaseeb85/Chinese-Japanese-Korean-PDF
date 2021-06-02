/**
 *
 */
package com.file.pdf;

import com.lr.bos.bo.FetchPurchaseOrderBO;

import java.util.List;
import java.util.Map;

/**
 * TableData
 *
 */
public class AuditsPoTableData {

    private List<String> headersList;

    private Map<Integer, List<FetchPurchaseOrderBO>> dataMap;

    private String totalItemNetValue;

    private String valueAddedTax;

    private String total;

    private String title;

    private String imagefileLocation;

    private byte[] imgByteArray;
   
    private String poTerms;
   
    private String totalItemLabel;
   
    private String taxlabel;
   
    private String totalLabel;
   
    private String poQuoted;
   
    private String billToAddr;


    /**
     * @return the headersList
     */
    public List<String> getHeadersList() {
        return headersList;
    }

    /**
     * @param headersList the headersList to set
     */
    public void setHeadersList(List<String> headersList) {
        this.headersList = headersList;
    }

    /**
     * @return the totalItemNetValue
     */
    public String getTotalItemNetValue() {
        return totalItemNetValue;
    }

    /**
     * @param totalItemNetValue the totalItemNetValue to set
     */
    public void setTotalItemNetValue(String totalItemNetValue) {
        this.totalItemNetValue = totalItemNetValue;
    }

    /**
     * @return the valueAddedTax
     */
    public String getValueAddedTax() {
        return valueAddedTax;
    }

    /**
     * @param valueAddedTax the valueAddedTax to set
     */
    public void setValueAddedTax(String valueAddedTax) {
        this.valueAddedTax = valueAddedTax;
    }

    /**
     * @return the total
     */
    public String getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(String total) {
        this.total = total;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the imagefileLocation
     */
    public String getImagefileLocation() {
        return imagefileLocation;
    }

    /**
     * @param imagefileLocation the imagefileLocation to set
     */
    public void setImagefileLocation(String imagefileLocation) {
        this.imagefileLocation = imagefileLocation;
    }

    /**
     * @return the dataMap
     */
    public Map<Integer, List<FetchPurchaseOrderBO>> getDataMap() {
        return dataMap;
    }

    /**
     * @param dataMap the dataMap to set
     */
    public void setDataMap(Map<Integer, List<FetchPurchaseOrderBO>> dataMap) {
        this.dataMap = dataMap;
    }


    /**
     * @return the imgByteArray
     */
    public byte[] getImgByteArray() {
        return imgByteArray;
    }

    /**
     * @param imgByteArray the imgByteArray to set
     */
    public void setImgByteArray(byte[] imgByteArray) {
        this.imgByteArray = imgByteArray;
    }
   
    public void setPoTerms(String poTerms) {
        this.poTerms = poTerms;
    }

    public String getPoTerms() {
        return poTerms;
    }

    public void setTotalItemLabel(String totalItemLabel) {
        this.totalItemLabel = totalItemLabel;
    }

    public String getTotalItemLabel() {
        return totalItemLabel;
    }

    public void setTaxlabel(String taxlabel) {
        this.taxlabel = taxlabel;
    }

    public String getTaxlabel() {
        return taxlabel;
    }

    public void setTotalLabel(String totalLabel) {
        this.totalLabel = totalLabel;
    }

    public String getTotalLabel() {
        return totalLabel;
    }

    public void setPoQuoted(String poQuoted) {
        this.poQuoted = poQuoted;
    }

    public String getPoQuoted() {
        return poQuoted;
    }

    public void setBillToAddr(String billToAddr) {
        this.billToAddr = billToAddr;
    }

    public String getBillToAddr() {
        return billToAddr;
    }
}

