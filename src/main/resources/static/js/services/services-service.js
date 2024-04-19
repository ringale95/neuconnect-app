import { makeRequest } from '../common/api.js';
import { showToast } from "../common/toast.js";

const basePath = '/api/services';

export const getListOfServices = (options, onSuccess, onError) => {
    makeRequest(basePath + '/fetch', 'POST', options, onSuccess, onError);
}
