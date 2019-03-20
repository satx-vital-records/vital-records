$(document).ready(function () {

    const apikey = 'AwfYCrCySAKmnFDNDv0Uiz';
    const client = filestack.init(apikey);
    const options = {
        onUploadDone: updateForm,
        maxSize: 10 * 1024 * 1024,
        accept: '.pdf',
        uploadInBackground: false,
        storeTo: {
            path: '/uploadedFiles/'
        }
    };
    const picker = client.picker(options);

        const form = document.getElementById('pick-form');
        const fileInput = document.getElementById('fileupload');
        const nameBox = document.getElementById('nameBox');
        const urlBox = document.getElementById('urlBox');
        let newUploadedFile ="";

        $('#picker').click(function (e) {
            e.preventDefault();
            picker.open();
        });

        form.addEventListener('submit', function (e) {
            e.preventDefault();
            alert('The following documents are being submitted to SATX Vital Records: ' + fileInput.value);

            newUploadedFile = fileInput.value;
            $('#fileupload').val(newUploadedFile);
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

