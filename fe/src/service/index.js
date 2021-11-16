import axios from "axios";

export class BaseApi {
    constructor(){
        this.url = 'http://localhost:8085/api/v1';
        this.entity = "";
    }

    /**
     * Hàm api lấy tất cả danh sách đối tượng
     * CreatedBy: PNANH (21/8/2021)
     */
     async getAll(){
        return await axios.get(`${this.url}/${this.entity}`)
    }
    /**
     * Hàm api lấy đối tượng theo Id
     * CreatedBy: PNANH (21/8/2021)
     */
    async getById(id){
        return await axios.get(`${this.url}/${this.entity}/${id}`)
    }
    /**
     * Hàm gọi api thêm đối tượng
     * CreatedBy: PNANH (21/8/2021)
     */
    async add(data){
        return await axios.post(`${this.url}/${this.entity}`, data)
    }
    /**
     * Hàm gọi api sửa đối tượng
     * CreatedBy: PNANH (21/8/2021)
     */
    async update(id, data){
        return await axios.put(`${this.url}/${this.entity}/${id}`, data)
    }
    /**
     * Hàm gọi api xóa đối tượng
     * CreatedBy: PNANH (21/8/2021)
     */
    async delete(id){
        return await axios.delete(`${this.url}/${this.entity}/${id}`)
    }
}