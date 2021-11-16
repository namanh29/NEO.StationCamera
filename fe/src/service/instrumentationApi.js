
import { BaseApi } from "./index";

class InstrumentationApi extends BaseApi{
    constructor(){
        super();
        this.entity = "instrumentation";
    }
}

export default new InstrumentationApi()