$(document).ready(function () {

let urlArray=[];
let urlImg1 = "";
let urlImg2 = "";

    const apikey = 'AwfYCrCySAKmnFDNDv0Uiz';
    const client = filestack.init(apikey);
    const options = {
        maxFiles: 20,
        uploadInBackground: false,
        onOpen: () => console.log('opened!'),
        //onUploadDone: (res) => console.log(res),
        // onUploadDone: (res) => console.log(res.filesUploaded[0].url),
        // onUploadDone: (res) => urlImg1 = res
        onUploadDone: function(res) {
            console.log("something");
            urlImg1= res.filesUploaded[0].url;
            urlImg2= res.filesUploaded[1].url;
            urlArray.push(urlImg1);
            urlArray.push(urlImg2);
            console.log(res);
            console.log(urlImg1, urlImg2);
        }
    };
    console.log(urlArray);
            // urlArray.push(urlImg1);
            // let urlArray = urlImg1;



    const picker = client.picker(options);

        const form = document.getElementById('pick-form');
        const fileInput = document.getElementById('fileupload');
        const fileInput2 = document.getElementById('fileupload2');
        const nameBox = document.getElementById('nameBox');
        const urlBox = document.getElementById('urlBox');

        $('#picker').click(function (e) {
            e.preventDefault();
            picker.open();
        });

        form.addEventListener('submit', function (e) {
                    // e.preventDefault();
                    // $(".alert").delay(1000).addClass("in").toggle(true).fadeOut(4000);
            alert('Your documents have been successfully submitted to SATX Vital Records');
            updateForm(urlImg1, urlImg2);

        });



        function updateForm (urlImg1, urlImg2) {
            // const fileData = result.filesUploaded[0];
            fileInput.value = urlImg1;
            fileInput2.value = urlImg2;

            // const name = document.createTextNode('Selected: ' + fileData.filename);
            const url1 = document.createElement('a');
            const url2 = document.createElement('a');
            // url.href = fileData.url;
            url.appendChild(document.createTextNode(urlImg1));
            url.appendChild(document.createTextNode(urlImg2));
            // nameBox.appendChild(name);
            // urlBox.appendChild(document.createTextNode('Uploaded to: '));
            // urlBox.appendChild(urlImg);
        }


});

// $(document).ready(function () {
//
//     const apikey = 'AwfYCrCySAKmnFDNDv0Uiz';
//     const client = filestack.init(apikey);
//     const options = {
//         maxFiles: 20,
//         uploadInBackground: false,
//         onOpen: () => console.log('opened!'),
//         //onUploadDone: (res) => console.log(res),
//         // onUploadDone: (res) => console.log(res.filesUploaded[0].url),
//         onUploadDone: updateForm,
//     };
//
//
//     const picker = client.picker(options);
//
//     const form = document.getElementById('pick-form');
//     const fileInput = document.getElementById('fileupload');
//     const nameBox = document.getElementById('nameBox');
//     const urlBox = document.getElementById('urlBox');
//
//     $('#picker').click(function (e) {
//         e.preventDefault();
//         picker.open();
//     });
//
//     form.addEventListener('submit', function (e) {
//         // e.preventDefault();
//         alert('The following documents are being submitted to SATX Vital Records: ' + urlImg);
//         // updateForm(urlImg);
//
//     });
//
//
//
//     function updateForm (result) {
//         console.log("hello");
//         const fileData = result.filesUploaded[0];
//         fileInput.value = fileData.url;
//         // fileInput.value = urlImg;
//         cpnsole.log(fileData);
//         cpnsole.log(fileInput.value);
//         cpnsole.log(result);
//
//         // const name = document.createTextNode('Selected: ' + fileData.filename);
//         const url = document.createElement('a');
//         // url.href = fileData.url;
//         url.appendChild(document.createTextNode(urlImg));
//         // nameBox.appendChild(name);
//         // urlBox.appendChild(document.createTextNode('Uploaded to: '));
//         // urlBox.appendChild(urlImg);
//     }
//
//
// });





