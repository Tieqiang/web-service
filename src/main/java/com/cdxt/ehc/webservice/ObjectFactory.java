//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.7 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2019.01.11 时间 10:53:17 PM CST 
//


package com.cdxt.ehc.webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.cdxt.ehc.webservice package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _RHCMessageServerRequest_QNAME = new QName("http://webservice.ehc.cdxt.com/", "RHCMessageServerRequest");
    private final static QName _RHCMessageServerResponse_QNAME = new QName("http://webservice.ehc.cdxt.com/", "RHCMessageServerResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.cdxt.ehc.webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RHCMessageServerRequest }
     * 
     */
    public RHCMessageServerRequest createRHCMessageServerRequest() {
        return new RHCMessageServerRequest();
    }

    /**
     * Create an instance of {@link RHCMessageServerResponse }
     * 
     */
    public RHCMessageServerResponse createRHCMessageServerResponse() {
        return new RHCMessageServerResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RHCMessageServerRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.ehc.cdxt.com/", name = "RHCMessageServerRequest")
    public JAXBElement<RHCMessageServerRequest> createRHCMessageServerRequest(RHCMessageServerRequest value) {
        return new JAXBElement<RHCMessageServerRequest>(_RHCMessageServerRequest_QNAME, RHCMessageServerRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RHCMessageServerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.ehc.cdxt.com/", name = "RHCMessageServerResponse")
    public JAXBElement<RHCMessageServerResponse> createRHCMessageServerResponse(RHCMessageServerResponse value) {
        return new JAXBElement<RHCMessageServerResponse>(_RHCMessageServerResponse_QNAME, RHCMessageServerResponse.class, null, value);
    }

}
