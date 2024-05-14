// file: password_visibility.js

function togglePasswordVisibility(event) {
    event.preventDefault(); // Ngăn chặn sự kiện mặc định của nút con mắt
    var passwordInput = event.target.previousElementSibling;
    if (passwordInput.type === "password") {
        passwordInput.type = "text";
        event.target.textContent = "👁️";
    } else {
        passwordInput.type = "password";
        event.target.textContent = "👁️";
    }
}

document.addEventListener("DOMContentLoaded", function () {
    const tableRows = document.querySelectorAll("#dataTable tbody tr");
    const rowsPerPage = 10;
    let currentPage = 0;
    const totalPages = Math.ceil(tableRows.length / rowsPerPage);
    const updateTvForm = document.getElementById('updateTvForm');
    const updateTvFormContent = document.getElementById('updateTvFormContent');
    const checkboxes = document.querySelectorAll('#dataTable tbody tr td input[type="checkbox"]');
    const TvButton = document.getElementById('TvButton');
    const pageNumbersContainer = document.getElementById('pageNumbers');
    const maThietBiMuonInput = document.getElementById('maThietBiMuon');
    const closeFormButton = document.getElementById('closeFormButton');

    closeFormButton.addEventListener('click', function () {
        hideupdateTvForm();
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

    function updateTvButtonStatus() {
        let checkedCount = 0;
        checkboxes.forEach(function (checkbox) {
            if (checkbox.checked) {
                checkedCount++;
            }
        });
        TvButton.disabled = checkedCount !== 1;
    }

    function showupdateTvForm() {
        updateTvForm.style.display = 'block';
    }

    function hideupdateTvForm() {
        updateTvForm.style.display = 'none';
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

    TvButton.addEventListener('click', function () {

        if (TvButton.disabled === false) {
            showupdateTvForm();
            updateMaThietBiMuonInput();
        }
    });

    // Sự kiện để ẩn form khi click ra ngoài form
    window.addEventListener('click', function (event) {
        if (event.target === updateTvForm) {
            hideupdateTvForm();
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
    updateTvButtonStatus();

    checkboxes.forEach(function (checkbox) {
        checkbox.addEventListener('click', function () {
            updateTvButtonStatus();
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
});
document.getElementById('TvButton').addEventListener('click', function () {
    // Lấy ra checkbox đã chọn
    const selectedCheckbox = document.querySelector('#dataTable tbody tr input[type="checkbox"]:checked');
    if (selectedCheckbox) {
        // Lấy ra hàng chứa checkbox đã chọn
        const selectedRow = selectedCheckbox.closest('tr');
        
        // Lấy ra các ô dữ liệu trong hàng đã chọn
        const cells = selectedRow.querySelectorAll('td');

        // Lấy ra các giá trị từ các ô dữ liệu
        const maThanhVien = cells[2].textContent;
        const tenThanhVien = cells[3].textContent;
        const khoa = cells[4].textContent;
        const nganh = cells[5].textContent;
        const sdt = cells[6].textContent;
        const passWord = cells[7].textContent;
        const email = cells[8].textContent;

        // Điền các giá trị vào các trường trong form chỉnh sửa
        document.getElementById('maThanhVien').value = maThanhVien;
        document.getElementById('hoTen').value = tenThanhVien;
        document.getElementById('khoa').value = khoa;
        document.getElementById('nganh').value = nganh;
        document.getElementById('sdt').value = sdt;
        document.getElementById('passWord').value = passWord;
        document.getElementById('email').value = email;

        // Hiển thị form chỉnh sửa
        document.getElementById('updateTvForm').style.display = 'block';
    } else {
        // Hiển thị thông báo nếu không có thành viên nào được chọn
        alert('Vui lòng chọn một thành viên để sửa.');
    }
});
// Thêm sự kiện click cho nút bấm con mắt
document.getElementById('togglePassword').addEventListener('click', function () {
    // Lấy trường password
    const passwordInput = document.getElementById('passWord');
    // Lấy biểu tượng con mắt
    const eyeIcon = document.getElementById('eyeIcon');

    // Thay đổi kiểu của trường password từ password sang text và ngược lại
    if (passwordInput.type === "password") {
        passwordInput.type = "text";
        eyeIcon.classList.remove('fa-eye');
        eyeIcon.classList.add('fa-eye-slash');
    } else {
        passwordInput.type = "password";
        eyeIcon.classList.remove('fa-eye-slash');
        eyeIcon.classList.add('fa-eye');
    }
});
document.addEventListener("DOMContentLoaded", function () {
    // Lắng nghe sự kiện khi click vào nút "Nhập Excel"
    document.getElementById("importExcelButton").addEventListener("click", function () {
        // Hiển thị hộp thoại chọn file
        document.getElementById("excelFileInput").click();
    });

    // Lắng nghe sự kiện khi người dùng chọn file từ hộp thoại
    document.getElementById("excelFileInput").addEventListener("change", function () {
        // Lấy thông tin file đã chọn
        var file = document.getElementById("excelFileInput").files[0];
        // Xử lý file ở đây (ví dụ: in ra tên file)
        console.log("Đã chọn file: " + file.name);
    });
});

document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('importExcelButton').addEventListener('click', function () {
        document.getElementById('excelFileInput').click();
    });

    document.getElementById('addMemberButton').addEventListener('click', function () {
        document.getElementById('addTvForm').style.display = 'block';
    });

    document.getElementById('closeAddFormButton').addEventListener('click', function () {
        document.getElementById('addTvForm').style.display = 'none';
    });

    document.getElementById('togglePassword').addEventListener('click', function () {
        const passwordField = document.getElementById('passWord');
        const eyeIcon = document.getElementById('eyeIcon');
        if (passwordField.type === 'password') {
            passwordField.type = 'text';
            eyeIcon.classList.remove('fa-eye');
            eyeIcon.classList.add('fa-eye-slash');
        } else {
            passwordField.type = 'password';
            eyeIcon.classList.remove('fa-eye-slash');
            eyeIcon.classList.add('fa-eye');
        }
    });

    document.getElementById('toggleAddPassword').addEventListener('click', function () {
        const passwordField = document.getElementById('addPassWord');
        const eyeIcon = document.getElementById('addEyeIcon');
        if (passwordField.type === 'password') {
            passwordField.type = 'text';
            eyeIcon.classList.remove('fa-eye');
            eyeIcon.classList.add('fa-eye-slash');
        } else {
            passwordField.type = 'password';
            eyeIcon.classList.remove('fa-eye-slash');
            eyeIcon.classList.add('fa-eye');
        }
    });
});
document.addEventListener('DOMContentLoaded', function () {
    const deleteButton = document.getElementById('deleteMemberButton');
    const checkboxes = document.querySelectorAll('input[type="checkbox"]');

    // Function to check if at least one checkbox is selected
    function checkCheckboxes() {
        let isChecked = false;
        checkboxes.forEach((checkbox) => {
            if (checkbox.checked) {
                isChecked = true;
            }
        });
        deleteButton.disabled = !isChecked;
    }

    // Add event listeners to checkboxes
    checkboxes.forEach((checkbox) => {
        checkbox.addEventListener('change', checkCheckboxes);
    });

    // Initial check
    checkCheckboxes();
});
