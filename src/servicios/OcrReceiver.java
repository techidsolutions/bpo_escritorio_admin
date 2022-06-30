
package servicios;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "OcrReceiver", targetNamespace = "http://tempuri.org/", wsdlLocation = "https://webservices.grupobc.com/webserviceocr/ocrreceiver.svc")
public class OcrReceiver
    extends Service
{

    private final static URL OCRRECEIVER_WSDL_LOCATION;
    private final static WebServiceException OCRRECEIVER_EXCEPTION;
    private final static QName OCRRECEIVER_QNAME = new QName("http://tempuri.org/", "OcrReceiver");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("https://webservices.grupobc.com/webserviceocr/ocrreceiver.svc");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        OCRRECEIVER_WSDL_LOCATION = url;
        OCRRECEIVER_EXCEPTION = e;
    }

    public OcrReceiver() {
        super(__getWsdlLocation(), OCRRECEIVER_QNAME);
    }

    public OcrReceiver(WebServiceFeature... features) {
        super(__getWsdlLocation(), OCRRECEIVER_QNAME, features);
    }

    public OcrReceiver(URL wsdlLocation) {
        super(wsdlLocation, OCRRECEIVER_QNAME);
    }

    public OcrReceiver(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, OCRRECEIVER_QNAME, features);
    }

    public OcrReceiver(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public OcrReceiver(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns IOcrReceiver
     */
    @WebEndpoint(name = "BasicHttpBinding_IOcrReceiver")
    public IOcrReceiver getBasicHttpBindingIOcrReceiver() {
        return super.getPort(new QName("http://tempuri.org/", "BasicHttpBinding_IOcrReceiver"), IOcrReceiver.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IOcrReceiver
     */
    @WebEndpoint(name = "BasicHttpBinding_IOcrReceiver")
    public IOcrReceiver getBasicHttpBindingIOcrReceiver(WebServiceFeature... features) {
        return super.getPort(new QName("http://tempuri.org/", "BasicHttpBinding_IOcrReceiver"), IOcrReceiver.class, features);
    }

    private static URL __getWsdlLocation() {
        if (OCRRECEIVER_EXCEPTION!= null) {
            throw OCRRECEIVER_EXCEPTION;
        }
        return OCRRECEIVER_WSDL_LOCATION;
    }

}
