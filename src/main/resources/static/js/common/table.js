export const reloadTable = (data) => {
    // Remove tbody from document
    const tableBody = document.querySelector('tbody');
    tableBody.innerHTML = '';

    // Create new tbody with tr based on data
    const newData = data.records;

    // Calculate the number of missing rows needed to reach 12
    const missingRows = Math.max(12 - newData.length, 0);

    // Loop to add rows for existing data
    newData.forEach(record => {
        const newRow = createTableRow(record);
        tableBody.appendChild(newRow);
    });

    // Add blank rows to fill the gap if needed
    for (let i = 0; i < missingRows; i++) {
        const blankRow = createBlankTableRow();
        tableBody.appendChild(blankRow);
    }
}

// Function to create a table row for existing data
function createTableRow(record) {
    const newRow = document.createElement('tr');
    newRow.innerHTML = `
        <td>${record.id}</td>
        <td>${record.fname} ${record.lname}</td>
        <td>${record.username}</td>
        <td>${record.role}</td>
        <td>
            <a href="/user-dashboard/manage-users/${record.id}/edit" class="btn btn-primary btn-sm">Edit</a>
        </td>
    `;
    return newRow;
}

// Function to create a blank table row
function createBlankTableRow() {
    const blankRow = document.createElement('tr');
    blankRow.innerHTML = `
        <td colspan="5" style="height: 48px;"></td>
    `;
    return blankRow;
}
