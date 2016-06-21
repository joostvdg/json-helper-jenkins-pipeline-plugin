package jenkins.plugins.json_helper.FromJsonStep
def f = namespace(lib.FormTagLib) as lib.FormTagLib

f.entry(field: 'jsonString', title: _('jsonString')) {
    f.textbox()
}