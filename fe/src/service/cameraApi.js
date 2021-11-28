import axios from "axios";
import { BaseApi } from "./index";

class CameraApi extends BaseApi {
    constructor() {
        super();
        this.entity = "camera";
    }
    async getByStationId(stationId) {
        return await axios.get(`${this.url}/${this.entity}/station_id/${stationId}`)
    }
    async getPagingAndFilter(stationId, filter, pageIndex, pageSize) {
        return await axios.get(`${this.url}/${this.entity}/station_id/${stationId}/filter?name=${filter.cameraName}&ins=${filter.insName}&status=${filter.statusName}&position=${filter.position}&pageindex=${pageIndex - 1}&pagesize=${pageSize}`)
    }
}

export default new CameraApi()