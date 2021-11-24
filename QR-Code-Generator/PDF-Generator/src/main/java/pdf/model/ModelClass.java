package pdf.model;

public class ModelClass {
    private String Uid;
    private String Gtin;
    private String Lot;
    private String ExpDate;

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getGtin() {
        return Gtin;
    }

    public void setGtin(String gtin) {
        Gtin = gtin;
    }

    public String getLot() {
        return Lot;
    }

    public void setLot(String lot) {
        Lot = lot;
    }

    public String getExpDate() {
        return ExpDate;
    }

    public void setExpDate(String expDate) {
        ExpDate = expDate;
    }
}
