import axios from "axios";
import { BaseApi } from "./index";

class StationApi extends BaseApi{
    constructor(){
        super();
        this.entity = "station";
    }

    async getPagingAndFilter(filter, pageIndex, pageSize) {
        return await axios.get(`${this.url}/${this.entity}/filter?objectType=${filter.objectType}&objectTypeShortname=${filter.objectTypeShortname}&stationCode=${filter.stationCode}&stationName=${filter.stationName}&longtitude=${filter.longtitude}&latitude=${filter.latitude}&provinceName=${filter.provinceName}&districtName=${filter.districtName}&wardName=${filter.wardName}&address=${filter.address}&riverName=${filter.riverName}&isActive=${filter.isActiveText}&pageindex=${pageIndex - 1}&pagesize=${pageSize}`)
    }
}

export default new StationApi()