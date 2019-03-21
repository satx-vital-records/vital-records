$(document).ready(function () {

    const apikey = 'AwfYCrCySAKmnFDNDv0Uiz';
    const client = filestack.init(apikey);
    const options = {
        maxFiles: 20,
        uploadInBackground: false,
        onOpen: () => console.log('opened!'),
        // onUploadDone: (res) => console.log(res.filesUploaded[0].url),
        onUploadDone: (res) => urlImg= res.filesUploaded[0].url
    };


    const picker = client.picker(options);

        const form = document.getElementById('pick-form');
        const fileInput = document.getElementById('fileupload');
        const nameBox = document.getElementById('nameBox');
        const urlBox = document.getElementById('urlBox');

        $('#picker').click(function (e) {
            e.preventDefault();
            picker.open();
        });

        form.addEventListener('submit', function (e) {
            // e.preventDefault();
            alert('The following documents are being submitted to SATX Vital Records: ' + urlImg);
            updateForm(urlImg);

        });



        function updateForm (urlImg) {
            // const fileData = result.filesUploaded[0];
            fileInput.value = urlImg;

            // const name = document.createTextNode('Selected: ' + fileData.filename);
            const url = document.createElement('a');
            // url.href = fileData.url;
            url.appendChild(document.createTextNode(urlImg));
            // nameBox.appendChild(name);
            // urlBox.appendChild(document.createTextNode('Uploaded to: '));
            // urlBox.appendChild(urlImg);
        }


});

