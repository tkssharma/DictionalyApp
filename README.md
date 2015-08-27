# Access SOAP service using KSOAP2 lib

```sh
POST /DictService/DictService.asmx HTTP/1.1
Host: services.aonaware.com
Content-Type: text/xml; charset=utf-8
Content-Length: length
SOAPAction: "http://services.aonaware.com/webservices/Define"

<?xml version="1.0" encoding="utf-8"?>
<soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
  <soap:Body>
    <Define xmlns="http://services.aonaware.com/webservices/">
      <word>string</word>
    </Define>
  </soap:Body>
</soap:Envelope>
```

# Android Studio App 

> Material Design Specifications[Navigation Drawer](http://blog.teamtreehouse.com/add-navigation-drawer-android) 
> Creating Apps with Material Design[Material Design](http://developer.android.com/training/material/index.html) 

## Running Locally
Make sure you have [Android studio/Eclipse ADB](http://developer.android.com/tools/studio/index.html) 

```sh
https://github.com/tkssharma/DictionaryApp.git
import project in app studio
run gradle build 
```

## Gradle Build
```sh
 repositories {
        maven { url 'http://ksoap2-android.googlecode.com/svn/m2-repo' }
    }
```

```sh
     compile 'com.android.support:appcompat-v7:23.0.0'
    compile 'com.google.code.ksoap2-android:ksoap2-android:3.1.1'

```
## DB Helper common Template

```sh
    private static final String SOAP_ACTION = "http://services.aonaware.com/webservices/Define";
    private static final String NAMESPACE = "http://services.aonaware.com/webservices/";
    private static final String MAIN_REQUEST_URL = "http://services.aonaware.com/DictService/DictService.asmx?op=Define";

    public String getCelsiusConversion(String fValue) {
        String data = null;
        String methodname = "Define";

        SoapObject request = new SoapObject(NAMESPACE, methodname);
        request.addProperty("word", fValue);

        SoapSerializationEnvelope envelope = getSoapSerializationEnvelope(request);

        HttpTransportSE ht = getHttpTransportSE();
        try {
            ht.call(SOAP_ACTION, envelope);
            //  testHttpResponse(ht);
            SoapObject resultsString = (SoapObject) envelope.getResponse();

           System.out.print(resultsString);

            data = resultsString.toString();

        } catch (SocketTimeoutException t) {
            t.printStackTrace();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (Exception q) {
            q.printStackTrace();
        }
        return data;
    }

```