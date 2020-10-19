<script>
    function quillEditor(elem, type, name) {
        var height = type == 'teacherInfo' ? '250px' : '500px';
        $(elem).css({'min-height': height, 'max-height': height});

        var toolbarOptions = type == 'teacherInfo' ? {container: [['bold', 'italic', 'underline', 'link']]} :
                {container: [['bold', 'italic', 'underline', 'strike'],
                    ['blockquote', 'code-block'],
                    [{ 'header': 1 }, { 'header': 2 }],
                    [{ 'list': 'ordered'}, { 'list': 'bullet' }],
                    [{ 'script': 'sub'}, { 'script': 'super' }],
                    [{ 'indent': '-1'}, { 'indent': '+1' }],
                    [{ 'direction': 'rtl' }],
                    [{ 'size': ['small', false, 'large', 'huge'] }],
                    [{ 'header': [1, 2, 3, 4, 5, 6, false] }],
                    [{ 'color': [] }, { 'background': [] }],
                    [{ 'font': [] }],
                    [{ 'align': [] }],
                    ['clean']
                ]}

        var quill = new Quill(elem, {
            modules: {
                "toolbar": toolbarOptions,
            },
            placeholder: 'Введіть текст сюди...',
            theme: 'snow',
        });

        var form = $(elem).parent();
        while(form[0].tagName.toUpperCase() != 'FORM') {
            form = $(form).parent();
        }

        $(form).on('submit', function() {
            $(this).find('textarea[name="' + name + '"]').val($('[data-quill-editor="' + type + '"] > .ql-editor').html());
        });
    }

    $(document).ready(function() {
        $('div[data-quill-editor="teacherInfo"]').each((id, elem) => {
            quillEditor(elem, 'teacherInfo', 'info');
        });

        $('div[data-quill-editor="departmentPage"]').each((id, elem) => {
            quillEditor(elem, 'departmentPage', 'body');
        });
    })
</script>