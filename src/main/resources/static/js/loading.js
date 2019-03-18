$(document).ready(function () {

    const apikey = 'AwfYCrCySAKmnFDNDv0Uiz';
    const client = filestack.init(apikey);
    const options = {
        onUploadDone: updateForm,
        maxSize: 10 * 1024 * 1024,
        accept: '.pdf',
        uploadInBackground: false,
        storeTo: {
            path:
        }
    };
    const picker = client.picker(options);

        const form = document.getElementById('pick-form');
        const fileInput = document.getElementById('fileupload');
        const nameBox = document.getElementById('nameBox');
        const urlBox = document.getElementById('urlBox');

        $('#picker').click(function (e) {
            e.preventDefault();
            console.log('hello');
            picker.open();
        });

        form.addEventListener('submit', function (e) {
            e.preventDefault();
            alert('Submitting: ' + fileInput.value);
        });

        function updateForm (result) {
            const fileData = result.filesUploaded[0];
            fileInput.value = fileData.url;

            const name = document.createTextNode('Selected: ' + fileData.filename);
            const url = document.createElement('a');
            url.href = fileData.url;
            url.appendChild(document.createTextNode(fileData.url));
            nameBox.appendChild(name);
            urlBox.appendChild(document.createTextNode('Uploaded to: '));
            urlBox.appendChild(url);
        }
});

