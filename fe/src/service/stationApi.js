import { BaseApi } from "./index";

class StationApi extends BaseApi{
    constructor(){
        super();
        this.entity = "station";
    }
}

export default new StationApi()