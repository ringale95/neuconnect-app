import { makeRequest } from '../common/api.js';
import { showToast } from "../common/toast.js";

const basePath = '/api/posts';

export const createPost = (post) => {
    const onSuccess = (data) => showToast('Post Created successfully', 'success');
    const onError = (data) => showToast('Request Failed', 'fail');
    makeRequest(basePath, 'POST', post, onSuccess, onError);
}

export const getPost = (id, onSuccess, onError) => {
    makeRequest(basePath + '/' + id, 'GET', post, onSuccess, onError);
}

export const getPaginatedLists = (options, onSuccess, onError) => {
    makeRequest(basePath + '/fetch', 'POST',options, onSuccess, onError);
}

export const upvoteThisPost = (id, onSuccess) => {
    const onError = (data) => showToast('Request Failed', 'fail');
    makeRequest(basePath + '/' + id + '/upvote', 'GET', {}, onSuccess, onError);
}

export const downvoteThisPost = (id, onSuccess) => {
    const onError = (data) => showToast('Request Failed', 'fail');
    makeRequest(basePath + '/' + id + '/downvote', 'GET', {}, onSuccess, onError);
}
