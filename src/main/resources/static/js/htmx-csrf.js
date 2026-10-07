// แนบ CSRF token ของ Spring Security ไปกับทุก request ที่ HTMX ส่ง 
document.addEventListener("htmx:configRequest", function (event) {
    const token = document.querySelector('meta[name="_csrf"]');
    const header = document.querySelector('meta[name="_csrf_header"]');
    if (token && header) {
        event.detail.headers[header.content] = token.content;
    }
});