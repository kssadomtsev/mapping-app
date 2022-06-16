
# Mapping Application
## Description

Mapping Application developed by test assignment requirements. Application accepts two command line arguments:
* source data file path
* output data file path

Some test were added in /test directory for avoid manual testing.

Example of source file content:
``
{
"productList":[
"CVCD",
"SDFD",
"DDDF",
"SDFD"
],
"mapping":{
"CVCD":{
"version":1,
"edition":"X"
},
"SDFD":{
"version":2,
"edition":"Z"
},
"DDDF":{
"version":1
}
}
}
``

Example of output file content:
``
[{"version":1,"edition":"X","quantity":1},{"version":1,"quantity":1},{"version":2,"edition":"Z","quantity":2}]
``


## Questions

Will be glad to discuss the application design and answer for all your questions! Please reach me by following contacts:
1. Email: kssadomtsev@gmail.com
2. Telegram: https://t.me/C0nstantineS
3. Phone: +79852280664
