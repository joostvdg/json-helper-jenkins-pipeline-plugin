# json-helper-jenkins-pipeline-plugin
A Jenkins plugin that is Pipeline compatible for json related help functions

Can convert json strings to a usable object and vice versa.

## writeObjectFromJson
Converts a JSON String to an Object.

**Usage** 
```
  def fooBar = writeObjectFromJson '{"foo" : "bar"}'
  def fooValue = fooBar.foo
```

## writeObjectToJson
You can supply it an object and it will write out a correct json, or return null.

**Usage**
```
    class Person {
        def name;
        def age;
    }
    node {
        def joost = new Person()
        joost.name = 'joost'
        joost.age = 10

        def jsonString = writeObjectToJson(joost)
        echo "jsonString=$jsonString"
    }
```
It will print out the following:
```
    jsonString={"name":"joost","age":10}
```
