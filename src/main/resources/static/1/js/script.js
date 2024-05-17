//jquery xoa thanh vien
$(document).ready(function () {
    $("a.delete_thanhvien").on("click", function (event) {
        var id = $(this).attr('id');
        if (confirm("Bạn Muốn Xóa Thành Viên " + id + "?")) {
        } else {
            event.preventDefault();
        }
    });
});


/*function checkViolationStatus() {
    // Gửi yêu cầu AJAX đến endpoint trên máy chủ
    $.ajax({
        url: "/endpoint/check_violation_status",
        type: "GET",
        success: function(response) {
            // Xử lý phản hồi từ máy chủ và hiển thị dữ liệu trạng thái vi phạm lên trang web
            if (response.locked) {
                $('#violationStatus').html('<p>Trạng thái: Đã bị khoá</p>');
            } else {
                $('#violationStatus').html('<p>Trạng thái: Không bị khoá</p>');
            }
        },
        error: function(xhr, status, error) {
            // Xử lý lỗi nếu có
            console.error("Error:", error);
        }
    });
}*/
function checkReservation() {
    // Gửi yêu cầu AJAX đến endpoint trên máy chủ
    $.ajax({
        url: "/endpoint/check_reservation",
        type: "GET",
        success: function(response) {
            // Xử lý phản hồi từ máy chủ và hiển thị dữ liệu đặt chỗ thiết bị lên trang web
            var tableHTML = '<table><tr><th>Mã thiết bị</th><th>Tên thiết bị</th></tr>';
            response.forEach(function(item) {
                tableHTML += '<tr><td>' + item.MaTB + '</td><td>' + item.TenTB + '</td></tr>';
            });
            tableHTML += '</table>';
            $('#reservationTable').html(tableHTML);
        },
        error: function(xhr, status, error) {
            // Xử lý lỗi nếu có
            console.error("Error:", error);
        }
    });
}
function checkBorrowedDevices() {
    // Gửi yêu cầu AJAX đến endpoint trên máy chủ
    $.ajax({
        url: "/endpoint/check_borrowed_devices",
        type: "GET",
        success: function(response) {
            // Xử lý phản hồi từ máy chủ và hiển thị thông tin về các thiết bị đang mượn lên trang web
            var tableHTML = '<table><tr><th>Mã thiết bị</th><th>Tên thiết bị</th></tr>';
            response.forEach(function(item) {
                tableHTML += '<tr><td>' + item.deviceID + '</td><td>' + item.deviceName + '</td></tr>';
            });
            tableHTML += '</table>';
            $('#borrowedDevicesTable').html(tableHTML);
        },
        error: function(xhr, status, error) {
            // Xử lý lỗi nếu có
            console.error("Error:", error);
        }
    });
}
function clearSearch() {
    window.location = "/thanhvien";
}

function clearSearchThietBi() {
    window.location = "/user";
}

//add thanh vien
$(document).ready(function() {
    $('#register').submit(function(e) {
        e.preventDefault();

        var data = {
            name: $('#name').val(),
            email: $('#email').val()
        };

        $.ajax({
            url: '/add',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function(response) {
                // Xử lý kết quả trả về từ controller
                if (response.success) {
                    alert('Thêm thành công');
                } else {
                    alert('Thêm thất bại');
                }
            }
        });
    });
});
/*document.addEventListener("DOMContentLoaded", function () {
    const tableRows = document.querySelectorAll("#dataTable tbody tr");
    const rowsPerPage = 10;
    let currentPage = 0;
    const totalPages = Math.ceil(tableRows.length / rowsPerPage);
    const muonForm = document.getElementById('muonForm');
    const muonFormContent = document.getElementById('muonFormContent');
    const checkboxes = document.querySelectorAll('#dataTable tbody tr td input[type="checkbox"]');
    const muonButton = document.getElementById('muonButton');
    const pageNumbersContainer = document.getElementById('pageNumbers');
    const maThietBiMuonInput = document.getElementById('maThietBiMuon');
    const closeFormButton = document.getElementById('closeFormButton');

    closeFormButton.addEventListener('click', function () {
        hideMuonForm();
    });
    function showPage(page) {
        const startIndex = page * rowsPerPage;
        const endIndex = Math.min(startIndex + rowsPerPage, tableRows.length);

        for (let i = 0; i < tableRows.length; i++) {
            if (i >= startIndex && i < endIndex) {
                tableRows[i].style.display = "table-row";
            } else {
                tableRows[i].style.display = "none";
            }
        }
    }

    function updatePageNumbers() {
        pageNumbersContainer.innerHTML = '';
        for (let i = 1; i <= totalPages; i++) {
            createPageNumberButton(i);
        }
    }

    function createPageNumberButton(pageNumber) {
        const pageNumberButton = document.createElement('button');
        pageNumberButton.textContent = pageNumber;
        pageNumberButton.addEventListener('click', function () {
            currentPage = pageNumber - 1;
            showPage(currentPage);
        });
        pageNumbersContainer.appendChild(pageNumberButton);
    }

    function updateMuonButtonStatus() {
        let checked = false;
        checkboxes.forEach(function (checkbox) {
            if (checkbox.checked) {
                checked = true;
            }
        });
        muonButton.disabled = !checked;
    }

    function showMuonForm() {
        muonForm.style.display = 'block';
    }

    function hideMuonForm() {
        muonForm.style.display = 'none';
    }

    function getSelectedThietBi() {
        const selectedThietBi = [];
        checkboxes.forEach(function (checkbox, index) {
            if (checkbox.checked) {
                const maThietBi = tableRows[index].querySelectorAll('td')[2].textContent;
                selectedThietBi.push(maThietBi);
            }
        });
        return selectedThietBi.join(', ');
    }

    function updateMaThietBiMuonInput() {
        maThietBiMuonInput.value = getSelectedThietBi();
    }

    muonButton.addEventListener('click', function () {
        if (muonButton.disabled === false) {
            showMuonForm();
            updateMaThietBiMuonInput();
        }
    });

    // Sự kiện để ẩn form khi click ra ngoài form
    window.addEventListener('click', function (event) {
        if (event.target === muonForm) {
            hideMuonForm();
        }
    });

    showPage(currentPage);

    document.getElementById("nextPage").addEventListener("click", function () {
        if (currentPage < totalPages - 1) {
            currentPage++;
            showPage(currentPage);
        }
    });

    document.getElementById("prevPage").addEventListener("click", function () {
        if (currentPage > 0) {
            currentPage--;
            showPage(currentPage);
        }
    });

    const prevButton = document.getElementById('prevPage');
    const nextButton = document.getElementById('nextPage');

    updatePageNumbers();
    updateMuonButtonStatus();

    checkboxes.forEach(function (checkbox) {
        checkbox.addEventListener('click', function () {
            updateMuonButtonStatus();
            updateMaThietBiMuonInput();
        });
    });

    prevButton.addEventListener('click', function () {
        if (currentPage > 0) {
            currentPage--;
            showPage(currentPage);
        }
    });

    nextButton.addEventListener('click', function () {
        if (currentPage < totalPages - 1) {
            currentPage++;
            showPage(currentPage);
        }
    });

});*/
function showChangePasswordModal() {
    // Lấy tham chiếu đến phần tử bảng cần hiển thị
    var changePasswordModal = document.getElementById('changePasswordModal');
    // Hiển thị bảng
    changePasswordModal.style.display = 'block';
}
function closeChangePasswordModal() {
    // Lấy tham chiếu đến phần tử bảng cần đóng
    var changePasswordModal = document.getElementById('changePasswordModal');
    // Đóng bảng
    changePasswordModal.style.display = 'none';
}
function showBorrowedDevicesTable() {
    // Lấy tham chiếu đến phần tử tbody của bảng
    var tableBody = document.getElementById('borrowedDevicesTableBody');

    // Thêm dữ liệu vào bảng
    var rowData = '<tr>' +
        '<td>520221</td>' +
        '<td>Tivi LG</td>' +
        '<td>1/4</td>' +
        '<td>8/4</td>' +
        '<td>Đã mượn</td>' +
        '</tr>';
    var rowData2 = '<tr>' +
        '<td>420213</td>' +
        '<td>Cassette TQ</td>' +
        '<td>15/3</td>' +
        '<td>22/3</td>' +
        '<td>Đang mượn</td>' +
        '</tr>';
    tableBody.innerHTML = rowData + rowData2;
    // Hiển thị bảng
    var borrowedDevicesModal = document.getElementById('borrowedDevicesModal');
    borrowedDevicesModal.style.display = 'block';
}

function closeBorrowedDevicesTable() {
    // Đóng bảng
    var borrowedDevicesModal = document.getElementById('borrowedDevicesModal');
    borrowedDevicesModal.style.display = 'none';
}
function showViolationStatus() {
    // Hiển thị bảng khi click vào nút
    var table = document.getElementById('violationStatusTable');
    table.style.display = 'block';

    // Thêm dữ liệu vào bảng
    var data = [
        { maThanhVien: 'TV001', ngayViPham: '30-4', xuLy: 'Bị khóa 7 ngày' },
        { maThanhVien: 'TV002', ngayViPham: '22-4', xuLy: 'Bị khóa 3 ngày' }
    ];

    var tableBody = document.getElementById('violationStatusTableBody');

    // Xóa bất kỳ dữ liệu cũ nào trong tbody
    tableBody.innerHTML = '';

    // Thêm dữ liệu mới vào tbody
    data.forEach(function(item) {
        var row = tableBody.insertRow();
        var cell1 = row.insertCell(0);
        var cell2 = row.insertCell(1);
        var cell3 = row.insertCell(2);
        cell1.innerHTML = item.maThanhVien;
        cell2.innerHTML = item.ngayViPham;
        cell3.innerHTML = item.xuLy;
    });
}
function closeViolationTable() {
    // Ẩn modal khi click vào nút đóng
    document.getElementById('violationStatusTable').style.display = 'none';
}
