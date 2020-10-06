package db;

import org.litepal.crud.LitePalSupport;

public class city extends LitePalSupport {
    private int id;
    private String cityNmame;
    private int cityCode;
    private int provinceId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityNmame() {
        return cityNmame;
    }

    public void setCityNmame(String cityNmame) {
        this.cityNmame = cityNmame;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }
}

