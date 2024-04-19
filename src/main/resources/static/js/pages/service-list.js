import { makeRequest } from '../common/api.js';
import { showToast } from "../common/toast.js";
import { getListOfServices } from "../services/services-service.js";
import { requestFailed } from "../common/errors.js";
import { renderServiceList } from "../components/service-list.js";

const baseOption = { pageNumber: 1, pageSize: 3 };
let filterOption = {};
let sortOption = {};

// Entry-point JS for /services/services-list
if (window.location.pathname.includes('/services/services-list')) {
  // Waits for DOM content to be loaded
  document.addEventListener('DOMContentLoaded', () => {

    // Execute services /fetch request
    getListOfServices(baseOption, renderServiceList, requestFailed);

    // Register eventHandler for filter Form submit action
    document.getElementById("filter-submit-form").addEventListener('submit', handleFilterSubmit);
    document.getElementById("next").addEventListener('click', (event) => handleButtons(1));
    document.getElementById("previous").addEventListener('click', (event) => handleButtons(-1));

  });
}

export const handleFilterSubmit = (event) => {
    event.preventDefault();

    const formData = new FormData(event.target);
    const formOptions = Object.fromEntries(formData.entries());
    const { searchKey, type, karmaMin, karmaMax, sortOption } = formOptions;

    // Initialize filterOption object
    filterOption = {};

    // Include options with data or values
    if (searchKey) filterOption.searchKey = searchKey;
    if (type) filterOption.type = type;
    if (karmaMin) filterOption.karmaMin = karmaMin;
    if (karmaMax) filterOption.karmaMax = karmaMax;

    // Split sortOption if it exists
    const splitSort = splitSortOption(sortOption);

    // Pass only the necessary options to getListOfServices
    getListOfServices({ ...baseOption, filterOption, sortOption: splitSort }, renderServiceList, requestFailed);
}


const handleButtons = (num) => {
    const pageCount = parseInt(document.getElementById("pageCount").textContent);
    const currentPageNumber = parseInt(document.getElementById("pageNumber").textContent);
    if(pageCount < currentPageNumber + num ||  currentPageNumber + num < 1){
        showToast("Invalid selection!", "fail");
        return;
    }
    const nextPaginationOption = {
        ...baseOption,
        pageNumber: parseInt(currentPageNumber) + num,
        filterOption,
        sortOption
    };
    getListOfServices(nextPaginationOption, renderServiceList, requestFailed);
}

const splitSortOption = (sortOption) => {
    const parts = sortOption.split(" - ");
    const key = parts[0];
    const type = parts[1];
    return { key, type };
}
