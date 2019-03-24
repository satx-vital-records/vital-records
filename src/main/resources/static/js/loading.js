// $(document).ready(function () {
//
// let urlArray=[];
//
//     const apikey = 'AwfYCrCySAKmnFDNDv0Uiz';
//     const client = filestack.init(apikey);
//     const options = {
//         maxFiles: 20,
//         uploadInBackground: false,
//         onOpen: () => console.log('opened!'),
//         onUploadDone: (res) => console.log(res),
//         // onUploadDone: (res) => urlImg= res.filesUploaded[0].url
//         // onUploadDone: function(res) {
//         //     console.log("something");
//         //     urlImg= res.filesUploaded[0].url;
//         //     urlImg2= res.filesUploaded[1].url;
//         //     urlArray.push(urlImg);
//         //     urlArray.push(urlImg2);
//         //     console.log(res);
//         //     console.log(urlImg, urlImg2);
//         // }
//     };
//
//
//     const picker = client.picker(options);
//
//         const form = document.getElementById('pick-form');
//         const fileInput = document.getElementById('fileupload');
//         const nameBox = document.getElementById('nameBox');
//         const urlBox = document.getElementById('urlBox');
//
//         $('#picker').click(function (e) {
//             e.preventDefault();
//             picker.open();
//         });
//
//         form.addEventListener('submit', function (e) {
//             // e.preventDefault();
//             alert('The following documents are being submitted to SATX Vital Records: ' + urlArray);
//             updateForm(urlArray);
//
//         });
//
//
//
//         function updateForm (urlArray) {
//             // const fileData = result.filesUploaded[0];
//             fileInput.value = urlArray;
//
//             // const name = document.createTextNode('Selected: ' + fileData.filename);
//             const url = document.createElement('a');
//             // url.href = fileData.url;
//             url.appendChild(document.createTextNode(urlArray));
//             // nameBox.appendChild(name);
//             // urlBox.appendChild(document.createTextNode('Uploaded to: '));
//             // urlBox.appendChild(urlImg);
//         }
//
//
// });

$(document).ready(function () {

    const apikey = 'AwfYCrCySAKmnFDNDv0Uiz';
    const client = filestack.init(apikey);
    const options = {
        maxFiles: 20,
        uploadInBackground: false,
        onOpen: () => console.log('opened!'),
        //onUploadDone: (res) => console.log(res),
        // onUploadDone: (res) => console.log(res.filesUploaded[0].url),
        onUploadDone: updateForm,
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
        // updateForm(urlImg);

    });



    function updateForm (result) {
        console.log("hello");
        const fileData = result.filesUploaded[0];
        fileInput.value = fileData.url;
        // fileInput.value = urlImg;
        cpnsole.log(fileData);
        cpnsole.log(fileInput.value);
        cpnsole.log(result);

        // const name = document.createTextNode('Selected: ' + fileData.filename);
        const url = document.createElement('a');
        // url.href = fileData.url;
        url.appendChild(document.createTextNode(urlImg));
        // nameBox.appendChild(name);
        // urlBox.appendChild(document.createTextNode('Uploaded to: '));
        // urlBox.appendChild(urlImg);
    }


});





