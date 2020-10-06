package db;

import org.litepal.crud.LitePalSupport;

public class city extends LitePalSupport {
    private int id;
    private String cityName;
    private int CityCode;
    private int ProvinceId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getcityName() {
        return cityName;
    }

    public void setcityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCityCode() {
        return CityCode;
    }

    public void setCityCode(int CityCode) {
        this.CityCode = CityCode;
    }

    public int  getProvinceId() {
        return ProvinceId;
    }

    public void setProvinceId(int ProvinceId) {
        this.ProvinceId = ProvinceId;
    }
}

