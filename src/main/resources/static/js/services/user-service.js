import { makeRequest, makeRequestFormData } from '../common/api.js';
import { showToast } from "../common/toast.js";

const basePath = '/api/users';

export const uploadCertificate = (formData) => {
    const onSuccess = (data) => showToast('Certificate uploaded successfully', 'success');
    const onError = (data) => showToast('Certificate not uploaded.', 'fail');
    const makeUploadRequestCallbackFunction = (data) => makeRequestFormData(basePath+'/'+data+'/certificates', 'POST', formData, onSuccess, onError);
    getUserId(makeUploadRequestCallbackFunction);
}

export const getUserId = (onSuccess) => {
    const onError = (data) => showToast('Request Failed', 'fail');
    makeRequest('/api/login/info', 'GET', {} , onSuccess, onError);
}