//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.0 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2006.05.07 at 10:58:45 AM PDT 
//


package com.fileobj.xml;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FindBugsSummary element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="FindBugsSummary">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element ref="{}PackageStats" maxOccurs="unbounded"/>
 *         &lt;/sequence>
 *         &lt;attribute name="clock_seconds" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *         &lt;attribute name="cpu_seconds" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *         &lt;attribute name="gc_seconds" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *         &lt;attribute name="num_packages" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *         &lt;attribute name="peak_mbytes" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *         &lt;attribute name="priority_2" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *         &lt;attribute name="priority_3" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *         &lt;attribute name="priority_4" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *         &lt;attribute name="timestamp" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *         &lt;attribute name="total_bugs" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *         &lt;attribute name="total_classes" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *         &lt;attribute name="total_size" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;/restriction>
 *     &lt;/complexContent>
 *   &lt;/complexType>
 * &lt;/element>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "packageStats"
})
@XmlRootElement(name = "FindBugsSummary")
public class FindBugsSummary {

    @XmlElement(name = "PackageStats")
    protected List<PackageStats> packageStats;
    @XmlAttribute(name = "clock_seconds", required = true)
    protected BigDecimal clockSeconds;
    @XmlAttribute(name = "cpu_seconds", required = true)
    protected BigDecimal cpuSeconds;
    @XmlAttribute(name = "gc_seconds", required = true)
    protected BigDecimal gcSeconds;
    @XmlAttribute(name = "num_packages", required = true)
    protected BigInteger numPackages;
    @XmlAttribute(name = "peak_mbytes", required = true)
    protected BigDecimal peakMbytes;
    @XmlAttribute(name = "priority_2", required = true)
    protected BigInteger priority2;
    @XmlAttribute(name = "priority_3", required = true)
    protected BigInteger priority3;
    @XmlAttribute(name = "priority_4", required = true)
    protected BigInteger priority4;
    @XmlAttribute(required = true)
    protected String timestamp;
    @XmlAttribute(name = "total_bugs", required = true)
    protected BigInteger totalBugs;
    @XmlAttribute(name = "total_classes", required = true)
    protected BigInteger totalClasses;
    @XmlAttribute(name = "total_size", required = true)
    protected BigInteger totalSize;

    /**
     * Gets the value of the packageStats property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the packageStats property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPackageStats().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PackageStats }
     * 
     * 
     */
    public List<PackageStats> getPackageStats() {
        if (packageStats == null) {
            packageStats = new ArrayList<PackageStats>();
        }
        return this.packageStats;
    }

    /**
     * Gets the value of the clockSeconds property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getClockSeconds() {
        return clockSeconds;
    }

    /**
     * Sets the value of the clockSeconds property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setClockSeconds(BigDecimal value) {
        this.clockSeconds = value;
    }

    /**
     * Gets the value of the cpuSeconds property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCpuSeconds() {
        return cpuSeconds;
    }

    /**
     * Sets the value of the cpuSeconds property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCpuSeconds(BigDecimal value) {
        this.cpuSeconds = value;
    }

    /**
     * Gets the value of the gcSeconds property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getGcSeconds() {
        return gcSeconds;
    }

    /**
     * Sets the value of the gcSeconds property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setGcSeconds(BigDecimal value) {
        this.gcSeconds = value;
    }

    /**
     * Gets the value of the numPackages property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumPackages() {
        return numPackages;
    }

    /**
     * Sets the value of the numPackages property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumPackages(BigInteger value) {
        this.numPackages = value;
    }

    /**
     * Gets the value of the peakMbytes property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPeakMbytes() {
        return peakMbytes;
    }

    /**
     * Sets the value of the peakMbytes property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPeakMbytes(BigDecimal value) {
        this.peakMbytes = value;
    }

    /**
     * Gets the value of the priority2 property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPriority2() {
        return priority2;
    }

    /**
     * Sets the value of the priority2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPriority2(BigInteger value) {
        this.priority2 = value;
    }

    /**
     * Gets the value of the priority3 property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPriority3() {
        return priority3;
    }

    /**
     * Sets the value of the priority3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPriority3(BigInteger value) {
        this.priority3 = value;
    }

    /**
     * Gets the value of the priority4 property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPriority4() {
        return priority4;
    }

    /**
     * Sets the value of the priority4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPriority4(BigInteger value) {
        this.priority4 = value;
    }

    /**
     * Gets the value of the timestamp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the value of the timestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimestamp(String value) {
        this.timestamp = value;
    }

    /**
     * Gets the value of the totalBugs property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTotalBugs() {
        return totalBugs;
    }

    /**
     * Sets the value of the totalBugs property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTotalBugs(BigInteger value) {
        this.totalBugs = value;
    }

    /**
     * Gets the value of the totalClasses property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTotalClasses() {
        return totalClasses;
    }

    /**
     * Sets the value of the totalClasses property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTotalClasses(BigInteger value) {
        this.totalClasses = value;
    }

    /**
     * Gets the value of the totalSize property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTotalSize() {
        return totalSize;
    }

    /**
     * Sets the value of the totalSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTotalSize(BigInteger value) {
        this.totalSize = value;
    }

}
