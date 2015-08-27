package com.tarunsoft.Dictapp.Util;
import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.IOException;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.util.List;


/**
 * Created by tsharma3 on 8/27/2015.
 */
public class Utility {

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
    private final void testHttpResponse(HttpTransportSE ht) {


    }
    private final SoapSerializationEnvelope getSoapSerializationEnvelope(SoapObject request) {
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.implicitTypes = true;
        envelope.setAddAdornments(false);
        envelope.setOutputSoapObject(request);

        return envelope;
    }
    private final HttpTransportSE getHttpTransportSE() {
        HttpTransportSE ht = new HttpTransportSE(Proxy.NO_PROXY,MAIN_REQUEST_URL,60000);
        ht.debug = true;
        ht.setXmlVersionTag("<!--?xml version=\"1.0\" encoding= \"UTF-8\" ?-->");
        return ht;
    }
}
