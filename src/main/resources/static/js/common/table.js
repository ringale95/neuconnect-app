export const reloadTable = (data) =>{
    // Remove tbody from document
    const tableBody = document.querySelector('tbody');
    tableBody.innerHTML = '';

    // Create new tbody with tr based on data
    const newData = data.records;
    newData.forEach(record => {
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
        tableBody.appendChild(newRow);
    });
}
