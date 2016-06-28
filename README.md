JSON Helper Plugin for Jenkins (Pipeline)
==========================================

A Jenkins plugin that is Pipeline compatible for json related help functions

Can convert json strings to a usable object and vice versa.


Requirements
============
 
* **Jenkins 1.639** or newer
* [Jenkins Pipeline Plugin](https://wiki.jenkins-ci.org/display/JENKINS/Pipeline+Plugin)

Usage
=====

Once installed, you can use it in a pipeline in-line script or Jenkinsfile.
For how to use it, see below or use the Snippet Generator.


## writeObjectFromJson
Converts a JSON String to an Object.

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

Maintainers
===========

* Joost van der Griendt ([Twitter](https://twitter.com/joost_vdg), [GitHub](https://github.com/joostvdg))

License
=======

[MIT License](LICENSE)