const hinh = [
    '1.png','2.png'
];
 let a = 0;
 function prev(){
    a--;
    if(a<0) a = hinh.length -1 ;
    document.getElementById('img').src = `img/${hinh[a]}`
 }
 function next(){
    a++;
    if(a==hinh.length) a =0 ;
    document.getElementById('img').src = `img/${hinh[a]}` 
 }
setInterval(next,4000)

   let user = JSON.parse(localStorage.getItem('user'))
   document.getElementById('user').innerHTML = user[0];