package xzhao.coolweather.util;

import android.text.TextUtils;

import xzhao.coolweather.db.CoolWeatherDB;
import xzhao.coolweather.model.City;
import xzhao.coolweather.model.County;
import xzhao.coolweather.model.Province;

/**
 * Created by Administrator on 2016/2/25.
 */
public class Utility {
    /*
     解析和处理服务器返回的省级数据
     */
    public synchronized static boolean handleProvincesResponse
    (CoolWeatherDB coolWeatherDB,String response){
        if (!TextUtils.isEmpty(response)){
            String[] allProvinces=response.split(",");
            if (allProvinces!=null&&allProvinces.length>0){
                for (String p:allProvinces){
                    String[] array=p.split("\\|");
                    Province province=new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);
                    //解析出来的数据存放到Province表
                    coolWeatherDB.saveProvince(province);
                }
                return true;
            }

        }
        return false;
    }

    public synchronized static boolean handleCitiesResponse
            (CoolWeatherDB coolWeatherDB,String response,int provinceId){
        if (!TextUtils.isEmpty(response)){
            String[] allCities=response.split(",");
            if (allCities!=null&&allCities.length>0){
                for (String p:allCities){
                    String[] array=p.split("\\|");
                    City city=new City();
                    city.setCityCode(array[0]);
                    city.setCityName(array[1]);
                    city.setId(provinceId);
                    //解析出来的数据存放到Province表
                    coolWeatherDB.saveCity(city);
                }
                return true;
            }

        }
        return false;
    }

    public synchronized static boolean handleCountiesResponse
            (CoolWeatherDB coolWeatherDB,String response,int cityId){
        if (!TextUtils.isEmpty(response)){
            String[] allCounties=response.split(",");
            if (allCounties!=null&&allCounties.length>0){
                for (String p:allCounties){
                    String[] array=p.split("\\|");
                    County county=new County();
                    county.setCountyCode(array[0]);
                    county.setCountyName(array[1]);
                    county.setCityId(cityId);
                    //解析出来的数据存放到Province表
                    coolWeatherDB.saveCounty(county);
                }
                return true;
            }

        }
        return false;
    }
}
