

/*
WARNING: THIS FILE IS AUTO-GENERATED. DO NOT MODIFY.

This file was generated from .idl 
using RTI Code Generator (rtiddsgen) version 3.1.0.
The rtiddsgen tool is part of the RTI Connext DDS distribution.
For more information, type 'rtiddsgen -help' at a command shell
or consult the Code Generator User's Manual.
*/

package ddsgen;

import com.rti.dds.infrastructure.*;
import com.rti.dds.infrastructure.Copyable;
import java.io.Serializable;
import com.rti.dds.cdr.CdrHelper;

public class HostSimple   implements Copyable, Serializable{

    public short id = (short)0;
    public short datacenterId = (short)0;
    public int timestampOfReport = (int)0;
    public int ram = (int)0;
    public int bw = (int)0;
    public int size = (int)0;
    public int peNumber = (int)0;
    public int mips = (int)0;

    public HostSimple() {

    }
    public HostSimple (HostSimple other) {

        this();
        copy_from(other);
    }

    public static java.lang.Object create() {

        HostSimple self;
        self = new  HostSimple();
        self.clear();
        return self;

    }

    public void clear() {

        id = (short)0;
        datacenterId = (short)0;
        timestampOfReport = (int)0;
        ram = (int)0;
        bw = (int)0;
        size = (int)0;
        peNumber = (int)0;
        mips = (int)0;
    }

    @Override
    public boolean equals(java.lang.Object o) {

        if (o == null) {
            return false;
        }        

        if(getClass() != o.getClass()) {
            return false;
        }

        HostSimple otherObj = (HostSimple)o;

        if(this.id != otherObj.id) {
            return false;
        }
        if(this.datacenterId != otherObj.datacenterId) {
            return false;
        }
        if(this.timestampOfReport != otherObj.timestampOfReport) {
            return false;
        }
        if(this.ram != otherObj.ram) {
            return false;
        }
        if(this.bw != otherObj.bw) {
            return false;
        }
        if(this.size != otherObj.size) {
            return false;
        }
        if(this.peNumber != otherObj.peNumber) {
            return false;
        }
        if(this.mips != otherObj.mips) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int __result = 0;
        __result += (int)id;
        __result += (int)datacenterId;
        __result += (int)timestampOfReport;
        __result += (int)ram;
        __result += (int)bw;
        __result += (int)size;
        __result += (int)peNumber;
        __result += (int)mips;
        return __result;
    }

    /**
    * This is the implementation of the <code>Copyable</code> interface.
    * This method will perform a deep copy of <code>src</code>
    * This method could be placed into <code>HostSimpleTypeSupport</code>
    * rather than here by using the <code>-noCopyable</code> option
    * to rtiddsgen.
    * 
    * @param src The Object which contains the data to be copied.
    * @return Returns <code>this</code>.
    * @exception NullPointerException If <code>src</code> is null.
    * @exception ClassCastException If <code>src</code> is not the 
    * same type as <code>this</code>.
    * @see com.rti.dds.infrastructure.Copyable#copy_from(java.lang.Object)
    */
    public java.lang.Object copy_from(java.lang.Object src) {

        HostSimple typedSrc = (HostSimple) src;
        HostSimple typedDst = this;

        typedDst.id = typedSrc.id;
        typedDst.datacenterId = typedSrc.datacenterId;
        typedDst.timestampOfReport = typedSrc.timestampOfReport;
        typedDst.ram = typedSrc.ram;
        typedDst.bw = typedSrc.bw;
        typedDst.size = typedSrc.size;
        typedDst.peNumber = typedSrc.peNumber;
        typedDst.mips = typedSrc.mips;

        return this;
    }

    @Override
    public java.lang.String toString(){
        return toString("", 0);
    }

    public java.lang.String toString(java.lang.String desc, int indent) {
        java.lang.StringBuffer strBuffer = new java.lang.StringBuffer();

        if (desc != null) {
            CdrHelper.printIndent(strBuffer, indent);
            strBuffer.append(desc).append(":\n");
        }

        CdrHelper.printIndent(strBuffer, indent+1);        
        strBuffer.append("id: ").append(this.id).append("\n");  
        CdrHelper.printIndent(strBuffer, indent+1);        
        strBuffer.append("datacenterId: ").append(this.datacenterId).append("\n");  
        CdrHelper.printIndent(strBuffer, indent+1);        
        strBuffer.append("timestampOfReport: ").append(this.timestampOfReport).append("\n");  
        CdrHelper.printIndent(strBuffer, indent+1);        
        strBuffer.append("ram: ").append(this.ram).append("\n");  
        CdrHelper.printIndent(strBuffer, indent+1);        
        strBuffer.append("bw: ").append(this.bw).append("\n");  
        CdrHelper.printIndent(strBuffer, indent+1);        
        strBuffer.append("size: ").append(this.size).append("\n");  
        CdrHelper.printIndent(strBuffer, indent+1);        
        strBuffer.append("peNumber: ").append(this.peNumber).append("\n");  
        CdrHelper.printIndent(strBuffer, indent+1);        
        strBuffer.append("mips: ").append(this.mips).append("\n");  

        return strBuffer.toString();
    }

}
