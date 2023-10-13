/*  xử lý sự kiện khi người dùng nhấp vào một mục trong menu bên.*/
const allSideMenu = document.querySelectorAll('#sidebar .side-menu.top li a');

allSideMenu.forEach(item => {
	const li = item.parentElement;

	item.addEventListener('click', function () {
		allSideMenu.forEach(i => {
			i.parentElement.classList.remove('active');
		})
		li.classList.add('active');
	})
});
// Dòng đầu tiên khai báo một hằng số "allSideMenu" bằng cách lựa chọn tất cả các phần tử <a> nằm trong các mục <li> trong menu bên. Điều này được thực hiện bằng cách sử dụng phương thức "querySelectorAll" trên phần tử có id là "sidebar", sau đó chọn các phần tử có lớp là "side-menu" và "top", cuối cùng là các phần tử <a>.

// Dòng thứ hai bắt đầu vòng lặp "forEach" để duyệt qua mỗi phần tử trong danh sách "allSideMenu".

// Trong mỗi lần lặp, dòng thứ tư lấy phần tử <li> cha của phần tử <a> đang xét và lưu vào biến "li".

// Dòng thứ sáu gắn một trình lắng nghe sự kiện "click" cho mỗi phần tử <a>. Khi người dùng nhấp vào một phần tử <a>, hàm được thực thi.

// Trong hàm xử lý sự kiện, dòng thứ tám bắt đầu một vòng lặp "forEach" khác trên danh sách "allSideMenu". Mục đích của vòng lặp này là loại bỏ lớp "active" khỏi tất cả các phần tử <li> trong danh sách để loại bỏ trạng thái "active" khỏi tất cả các mục menu.

// Sau đó, dòng thứ chín thêm lớp "active" vào phần tử <li> cha của phần tử <a> mà người dùng đã nhấp vào. Điều này đảm bảo rằng mục menu được nhấp vào được đánh dấu là "active".



// TOGGLE SIDEBAR xử lý sự kiện khi người dùng nhấp vào biểu tượng menu trong thanh điều hướng
const menuBar = document.querySelector('#content nav .bx.bx-menu');
const sidebar = document.getElementById('sidebar');

menuBar.addEventListener('click', function () {
	sidebar.classList.toggle('hide');
})
// Dòng đầu tiên khai báo một hằng số "menuBar" bằng cách lựa chọn phần tử đầu tiên phù hợp với điều kiện trong câu truy vấn CSS, đó là phần tử có id là "content", có thẻ là "nav" và có lớp là "bx bx-menu". Điều này được thực hiện bằng cách sử dụng phương thức "querySelector" trên đối tượng document.

// Dòng thứ hai lấy tham chiếu đến phần tử có id là "sidebar" và lưu vào biến "sidebar".

// Dòng thứ tư gắn một trình lắng nghe sự kiện "click" cho phần tử "menuBar". Khi người dùng nhấp vào biểu tượng menu, hàm được thực thi.

// Trong hàm xử lý sự kiện, dòng thứ sáu sử dụng phương thức "classList.toggle" trên phần tử "sidebar" để thêm hoặc loại bỏ lớp "hide". Lớp "hide" có thể có ý nghĩa là ẩn hiện thanh bên (sidebar) tùy thuộc vào trạng thái hiện tại của nó. Nếu thanh bên đang được ẩn, lớp "hide" sẽ được thêm vào và nếu thanh bên đang hiển thị, lớp "hide" sẽ bị loại bỏ.





//xử lý sự kiện và thay đổi trạng thái của các phần tử trong giao diện người dùng dựa trên kích thước cửa sổ trình duyệt

const searchButton = document.querySelector('#content nav form .form-input button');
const searchButtonIcon = document.querySelector('#content nav form .form-input button .bx');
const searchForm = document.querySelector('#content nav form');

searchButton.addEventListener('click', function (e) {
	if (window.innerWidth < 576) {
		e.preventDefault();
		searchForm.classList.toggle('show');
		if (searchForm.classList.contains('show')) {
			searchButtonIcon.classList.replace('bx-search', 'bx-x');
		} else {
			searchButtonIcon.classList.replace('bx-x', 'bx-search');
		}
	}
})





if (window.innerWidth < 768) {
	sidebar.classList.add('hide');
} else if (window.innerWidth > 576) {
	searchButtonIcon.classList.replace('bx-x', 'bx-search');
	searchForm.classList.remove('show');
}


window.addEventListener('resize', function () {
	if (this.innerWidth > 576) {
		searchButtonIcon.classList.replace('bx-x', 'bx-search');
		searchForm.classList.remove('show');
	}
})


// chuyển đổi màu sắc body
const switchMode = document.getElementById('switch-mode');

switchMode.addEventListener('change', function () {
	if (this.checked) {
		document.body.classList.add('dark');
	} else {
		document.body.classList.remove('dark');
	}
})

//sử lý thêm tk
	  const add=document.querySelector('.js-them') 
<<<<<<< HEAD
          const modalthem=document.querySelector('.modalthem')
=======
<<<<<<< HEAD
          const modalthem=document.querySelector('.modalthem')
=======
      const modalthem=document.querySelector('.modalthem')
>>>>>>> afba4a65b1b2fc7c969577b7bd7ecb26bad8e076
>>>>>>> 28a0da9dee07f49a67e9abde61520c936320e33b
	  const modalClosethem=document.querySelector('.js-modalclosethem')
	  function showadd(){
		modalthem.classList.add('open')
	  }
	  function closeadd(){
		modalthem.classList.remove('open')
	  }
	  add.addEventListener('click',showadd)
	  modalClosethem.addEventListener('click',closeadd)

// //sử lý sửa danh mục
const updates=document.querySelectorAll('.js-sua')
const modal=document.querySelector('.modalsua')
const modalclose=document.querySelector('.js-modalclosesua')

function showupdate(){
	modal.classList.add('open')
}
function closeupdate(){
	modal.classList.remove('open')
}
for(const update of updates){
	update.addEventListener('click',showupdate)
}
<<<<<<< HEAD
modalclose.addEventListener('click',closeupdate)
=======
modalclose.addEventListener('click',closeupdate)





>>>>>>> afba4a65b1b2fc7c969577b7bd7ecb26bad8e076
