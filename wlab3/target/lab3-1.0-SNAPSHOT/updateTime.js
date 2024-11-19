document.addEventListener('DOMContentLoaded',function(){
function updateDateTime(){
    const now = new Date();
    const formattedDateTime = now.toLocaleString();
    document.getElementById('datetime').textContent=formattedDateTime;
}
updateDateTime();
setInterval(updateDateTime,7000);});
