import { makeRequest } from '../common/api.js';
import { showToast } from "../common/toast.js";
import { reloadTable } from "../common/table.js";

if (window.location.pathname.includes('/manage-users')) {
  // Use DOMContentLoaded directly in signup.js
  document.addEventListener('DOMContentLoaded', () => {

    // Attach the onSubmit function to the button click event
    const searchButton = document.getElementById('search-button');
    if (searchButton) {
      searchButton.addEventListener('click', loadResults);
    }

  });
}

const loadResults = (event) => {
    const searchText = document.getElementById('search-field').value;
    const options = {
        pageNumber : 1,
        pageSize : 12,
        filterOption:{
            searchKey: searchText
        }
    }
  // Invoke makeRequest(url, method, formData, onSuccess, onError)
  makeRequest('/api/users/fetch' , 'POST', options, onSuccess, onError);

}

// Define the onSuccess and onError functions as needed
const onSuccess = (data) => {
    reloadTable(data);

};

const onError = (error) => {
  showToast("Request Failed", "fail");
};

export { loadResults };
