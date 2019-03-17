"use strict";

$(document).ready(function () {

    window.addEventListener('DOMContentLoaded', function () {
    const apikey = AwfYCrCySAKmnFDNDv0Uiz;
    const client = filestack.init(apikey);

    const onProgress = (evt) => {
        $('#progress').innerHTML = `${evt.totalPercent}%`;
    };

    document.querySelector('input').addEventListener('change', (event) => {
        const files = event.target.files;
        const file = files.item(0);
        const token = {};
        const cancel = $('#cancel');
        const pause = $('#pause');
        const resume = $('#resume');

    [cancel, resume, pause].forEach((btn) => {
        const id = btn.id;
    btn.addEventListener('click', () => {
        token[id]();
    });
    });

    client.upload(file, { onProgress }, {}, token)
        .then(res => {
        console.log('success: ', res)
})
.catch(err => {
        console.log(err)
});
});
});

});

