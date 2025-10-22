const hinh = [
    'banner1.png',
    'banner2.png',
    'banner3.png'
];

let a = 0;

function prev() {
    a--;
    if (a < 0) a = hinh.length - 1;
    document.getElementById('img').style.opacity = 0;
    setTimeout(() => {
        document.getElementById('img').src = `/img/${hinh[a]}`;
        document.getElementById('img').style.opacity = 1;
    }, 300);
}

function next() {
    a++;
    if (a === hinh.length) a = 0;
    document.getElementById('img').style.opacity = 0;
    setTimeout(() => {
        document.getElementById('img').src = `/img/${hinh[a]}`;
        document.getElementById('img').style.opacity = 1;
    }, 300);
}

// Tự động chuyển banner mỗi 4 giây
setInterval(next, 4000);

// Gán user nếu có trong localStorage
let user = JSON.parse(localStorage.getItem('user'));
if (user && user.length > 0) {
    const el = document.getElementById('user');
    if (el) el.innerHTML = user[0];
}
