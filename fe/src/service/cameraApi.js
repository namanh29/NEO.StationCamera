import axios from "axios";
import { BaseApi } from "./index";

class CameraApi extends BaseApi{
    constructor(){
        super();
        this.entity = "camera";
    }
    async getByStationId(stationId){
        return await axios.get(`${this.url}/${this.entity}/station_id/${stationId}`)
    }
}

export default new CameraApi()