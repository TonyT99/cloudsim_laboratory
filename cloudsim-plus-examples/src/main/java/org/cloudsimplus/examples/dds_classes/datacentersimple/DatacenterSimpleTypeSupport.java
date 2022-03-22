
/*
WARNING: THIS FILE IS AUTO-GENERATED. DO NOT MODIFY.

This file was generated from .idl
using RTI Code Generator (rtiddsgen) version 3.1.0.
The rtiddsgen tool is part of the RTI Connext DDS distribution.
For more information, type 'rtiddsgen -help' at a command shell
or consult the Code Generator User's Manual.
*/

package org.cloudsimplus.examples.dds_classes.datacentersimple;

import com.rti.dds.cdr.CdrEncapsulation;
import com.rti.dds.cdr.CdrInputStream;
import com.rti.dds.cdr.CdrOutputStream;
import com.rti.dds.cdr.CdrPrimitiveType;
import com.rti.dds.cdr.CdrBuffer;
import com.rti.dds.cdr.IllegalCdrStateException;
import com.rti.dds.publication.DataWriter;
import com.rti.dds.publication.DataWriterListener;
import com.rti.dds.subscription.DataReader;
import com.rti.dds.subscription.DataReaderListener;
import com.rti.dds.topic.KeyHash_t;
import com.rti.dds.topic.DefaultEndpointData;
import com.rti.dds.topic.TypeSupportImpl;
import com.rti.dds.topic.TypeSupportType;
import com.rti.dds.infrastructure.RETCODE_ERROR;
import com.rti.dds.topic.PrintFormatProperty;
import com.rti.dds.typecode.TypeCode;
import com.rti.dds.typecode.ExtensibilityKind;

import com.rti.dds.topic.TypeSupportParticipantInfo;
import com.rti.dds.topic.TypeSupportEndpointInfo;
import com.rti.dds.domain.DomainParticipant;

/**
* A collection of useful methods for dealing with objects of type
* datacentersimple
*/

public class DatacenterSimpleTypeSupport extends TypeSupportImpl {
    // -----------------------------------------------------------------------
    // Private Fields
    // -----------------------------------------------------------------------

    private static final java.lang.String TYPE_NAME = "datacentersimple";

    private static final char[] PLUGIN_VERSION = {2, 0, 0, 0};
    private static final DatacenterSimpleTypeSupport _singleton
    = new DatacenterSimpleTypeSupport();

    // -----------------------------------------------------------------------
    // Public Methods
    // -----------------------------------------------------------------------

    // --- External methods: -------------------------------------------------
    /* The methods in this section are for use by users of RTI Connext
    */

    public static java.lang.String get_type_name() {
        return _singleton.get_type_nameI();
    }

    public static void register_type(DomainParticipant participant,
    java.lang.String type_name) {
        _singleton.register_typeI(participant, type_name);
    }

    public static void unregister_type(DomainParticipant participant,
    java.lang.String type_name) {
        _singleton.unregister_typeI(participant, type_name);
    }

    /* The methods in this section are for use by RTI Connext
    * itself and by the code generated by rtiddsgen for other types.
    * They should be used directly or modified only by advanced users and are
    * subject to change in future versions of RTI Connext.
    */
    public static DatacenterSimpleTypeSupport get_instance() {
        return _singleton;
    }

    public static DatacenterSimpleTypeSupport getInstance() {
        return get_instance();
    }

    public static TypeCode getTypeCode(){
        return DatacenterSimpleTypeCode.VALUE;
    }

    @Override
    public java.lang.Object create_data() {
        return DdsDatacenterSimple.create();
    }

    @Override
    public void destroy_data(java.lang.Object data) {
        return;
    }
    @Override
    public java.lang.Object create_key() {
        return new DdsDatacenterSimple();
    }

    @Override
    public void destroy_key(java.lang.Object key) {
        return;
    }

    @Override
    public java.lang.Class<?> get_type() {
        return DdsDatacenterSimple.class;
    }

    /**
    * This is a concrete implementation of this method inherited from the base class.
    * This method will perform a deep copy of <code>source</code> into
    * <code>destination</code>.
    *
    * @param source The Object which contains the data to be copied.
    * @return Returns <code>destination</code>.
    * @exception NullPointerException If <code>destination</code> or
    * <code>source</code> is null.
    * @exception ClassCastException If either <code>destination</code> or
    * <code>this</code> is not a <code>datacentersimple</code>
    * type.
    */
    @Override
    public java.lang.Object copy_data(java.lang.Object destination, java.lang.Object source) {

        DdsDatacenterSimple typedDst = (DdsDatacenterSimple) destination;
        DdsDatacenterSimple typedSrc = (DdsDatacenterSimple) source;

        return typedDst.copy_from(typedSrc);

    }

    @Override
    public long get_serialized_sample_max_size(java.lang.Object endpoint_data,boolean include_encapsulation,short final_encapsulation_id,long currentAlignment) {
        CdrPrimitiveType _cdrPrimitiveType = CdrPrimitiveType.getInstance(final_encapsulation_id);
        short encapsulation_id = CdrEncapsulation.getEncapsulationFromFinal(
            final_encapsulation_id,
            ExtensibilityKind.EXTENSIBLE_EXTENSIBILITY);
        boolean xcdr1 = (encapsulation_id <= CdrEncapsulation.CDR_ENCAPSULATION_ID_PL_CDR_LE);
        long origAlignment = currentAlignment;
        long encapsulation_size = currentAlignment;

        if(include_encapsulation) {
            if (!CdrEncapsulation.isValidEncapsulationKind(encapsulation_id)) {
                throw new RETCODE_ERROR("Unsupported encapsulation");
            }

            encapsulation_size += _cdrPrimitiveType.getShortMaxSizeSerialized(encapsulation_size);
            encapsulation_size += _cdrPrimitiveType.getShortMaxSizeSerialized(encapsulation_size);
            encapsulation_size -= currentAlignment;
            currentAlignment = 0;
            origAlignment = 0;
        }

        if (!xcdr1) {
            //DHeader
            currentAlignment += _cdrPrimitiveType.getIntMaxSizeSerialized(currentAlignment);
        }

        currentAlignment += _cdrPrimitiveType.getIntMaxSizeSerialized(currentAlignment) ;
        currentAlignment += _cdrPrimitiveType.getIntMaxSizeSerialized(currentAlignment) ;
        if (include_encapsulation) {
            currentAlignment += encapsulation_size;
        }
        return  currentAlignment - origAlignment;
    }

    @Override
    public long get_serialized_sample_min_size(java.lang.Object endpoint_data,boolean include_encapsulation,short final_encapsulation_id,long currentAlignment) {
        CdrPrimitiveType _cdrPrimitiveType = CdrPrimitiveType.getInstance(final_encapsulation_id);
        short encapsulation_id = CdrEncapsulation.getEncapsulationFromFinal(
            final_encapsulation_id,
            ExtensibilityKind.EXTENSIBLE_EXTENSIBILITY);
        boolean xcdr1 = (encapsulation_id <= CdrEncapsulation.CDR_ENCAPSULATION_ID_PL_CDR_LE);
        long origAlignment = currentAlignment;
        long encapsulation_size = currentAlignment;

        if(include_encapsulation) {
            if (!CdrEncapsulation.isValidEncapsulationKind(encapsulation_id)) {
                throw new RETCODE_ERROR("Unsupported encapsulation");
            }

            encapsulation_size += _cdrPrimitiveType.getShortMaxSizeSerialized(encapsulation_size);
            encapsulation_size += _cdrPrimitiveType.getShortMaxSizeSerialized(encapsulation_size);
            encapsulation_size -= currentAlignment;
            currentAlignment = 0;
            origAlignment = 0;
        }

        if (!xcdr1) {
            //DHeader
            currentAlignment += _cdrPrimitiveType.getIntMaxSizeSerialized(currentAlignment);
        }

        currentAlignment +=_cdrPrimitiveType.getIntMaxSizeSerialized(currentAlignment) ;
        currentAlignment +=_cdrPrimitiveType.getIntMaxSizeSerialized(currentAlignment) ;

        if (include_encapsulation) {
            currentAlignment += encapsulation_size;
        }
        return  currentAlignment - origAlignment;
    }

    @Override
    public long get_serialized_sample_size(
        java.lang.Object endpoint_data, boolean include_encapsulation,
        short final_encapsulation_id, long currentAlignment,
        java.lang.Object sample)
    {

        CdrPrimitiveType _cdrPrimitiveType = CdrPrimitiveType.getInstance(final_encapsulation_id);
        short encapsulation_id = CdrEncapsulation.getEncapsulationFromFinal(
            final_encapsulation_id,
            ExtensibilityKind.EXTENSIBLE_EXTENSIBILITY);
        boolean xcdr1 = (encapsulation_id <= CdrEncapsulation.CDR_ENCAPSULATION_ID_PL_CDR_LE);
        DdsDatacenterSimple typedSrc = (DdsDatacenterSimple) sample;
        DefaultEndpointData epd = ((DefaultEndpointData) endpoint_data) ;
        long origAlignment = currentAlignment;
        long encapsulation_size = currentAlignment;

        if(include_encapsulation) {
            if (!CdrEncapsulation.isValidEncapsulationKind(encapsulation_id)) {
                throw new RETCODE_ERROR("Unsupported encapsulation");
            }

            encapsulation_size += _cdrPrimitiveType.getShortMaxSizeSerialized(encapsulation_size);
            encapsulation_size += _cdrPrimitiveType.getShortMaxSizeSerialized(encapsulation_size);
            encapsulation_size -= currentAlignment;
            currentAlignment = 0;
            origAlignment = 0;
            if(xcdr1){
                epd.setBaseAlignment(currentAlignment);
            }
        }

        if (!xcdr1) {
            //DHeader
            currentAlignment += _cdrPrimitiveType.getIntMaxSizeSerialized(currentAlignment);
        }

        currentAlignment  +=  _cdrPrimitiveType.getIntMaxSizeSerialized(epd.getAlignment(currentAlignment));

        currentAlignment  +=  _cdrPrimitiveType.getIntMaxSizeSerialized(epd.getAlignment(currentAlignment));

        if (include_encapsulation) {
            currentAlignment += encapsulation_size;
        }
        return currentAlignment - origAlignment;
    }

    @Override
    public long get_serialized_key_max_size(
        java.lang.Object endpoint_data,
        boolean include_encapsulation,
        short final_encapsulation_id,
        long currentAlignment)
    {
        CdrPrimitiveType _cdrPrimitiveType = CdrPrimitiveType.getInstance(final_encapsulation_id);
        short encapsulation_id = CdrEncapsulation.getEncapsulationFromFinal(
            final_encapsulation_id,
            ExtensibilityKind.EXTENSIBLE_EXTENSIBILITY);
        boolean xcdr1 = (encapsulation_id <= CdrEncapsulation.CDR_ENCAPSULATION_ID_PL_CDR_LE);
        long origAlignment = currentAlignment;
        long encapsulation_size = currentAlignment;

        if(include_encapsulation) {
            if (!CdrEncapsulation.isValidEncapsulationKind(encapsulation_id)) {
                throw new RETCODE_ERROR("Unsupported encapsulation");
            }

            encapsulation_size += _cdrPrimitiveType.getShortMaxSizeSerialized(encapsulation_size);
            encapsulation_size += _cdrPrimitiveType.getShortMaxSizeSerialized(encapsulation_size);
            encapsulation_size -= currentAlignment;
            currentAlignment = 0;
            origAlignment = 0;
        }

        if (!xcdr1) {
            //DHeader
            currentAlignment += _cdrPrimitiveType.getIntMaxSizeSerialized(currentAlignment);
        }

        currentAlignment +=  _cdrPrimitiveType.getIntMaxSizeSerialized(currentAlignment) ;
        if (include_encapsulation) {
            currentAlignment += encapsulation_size;
        }

        return currentAlignment - origAlignment;
    }

    @Override
    public long get_serialized_key_for_keyhash_max_size(
        java.lang.Object endpoint_data,
        boolean include_encapsulation,
        short final_encapsulation_id,
        long currentAlignment)
    {

        CdrPrimitiveType _cdrPrimitiveType = CdrPrimitiveType.getInstance(final_encapsulation_id);
        short encapsulation_id = CdrEncapsulation.getEncapsulationFromFinal(
            final_encapsulation_id,
            ExtensibilityKind.EXTENSIBLE_EXTENSIBILITY);
        boolean xcdr1 = (encapsulation_id <= CdrEncapsulation.CDR_ENCAPSULATION_ID_PL_CDR_LE);
        if (xcdr1){
            return get_serialized_key_max_size( endpoint_data, include_encapsulation, final_encapsulation_id, currentAlignment) ;
        }
        long origAlignment = currentAlignment;
        long encapsulation_size = currentAlignment;

        if(include_encapsulation) {
            if (!CdrEncapsulation.isValidEncapsulationKind(encapsulation_id)) {
                throw new RETCODE_ERROR("Unsupported encapsulation");
            }

            encapsulation_size += _cdrPrimitiveType.getShortMaxSizeSerialized(encapsulation_size);
            encapsulation_size += _cdrPrimitiveType.getShortMaxSizeSerialized(encapsulation_size);
            encapsulation_size -= currentAlignment;
            currentAlignment = 0;
            origAlignment = 0;
        }

        currentAlignment += _cdrPrimitiveType.getIntMaxSizeSerialized(currentAlignment) ;
        if (include_encapsulation) {
            currentAlignment += encapsulation_size;
        }

        return currentAlignment - origAlignment;

    }

    @Override
    public void serialize(java.lang.Object endpoint_data,java.lang.Object src,
    CdrOutputStream dst, boolean serialize_encapsulation, short final_encapsulation_id,
    boolean serialize_sample, java.lang.Object endpoint_plugin_qos) {
        int position = 0;
        int dheaderPosition = -1;
        boolean inBaseClass_tmp = false;
        inBaseClass_tmp =  dst.inBaseClass;
        dst.inBaseClass = false;

        if(serialize_encapsulation) {
            dst.serializeAndSetCdrEncapsulation(final_encapsulation_id, ExtensibilityKind.EXTENSIBLE_EXTENSIBILITY);
            position = dst.resetAlignment();
        }

        if(serialize_sample) {
            boolean xcdr1 = (final_encapsulation_id <= CdrEncapsulation.CDR_ENCAPSULATION_ID_PL_CDR_LE)? true: false;

            if (!inBaseClass_tmp && !xcdr1) {
                dheaderPosition=dst.writeDHeader();
            }
            DdsDatacenterSimple typedSrc = (DdsDatacenterSimple) src;

            dst.writeInt(typedSrc.id);
            dst.writeInt(typedSrc.timestampOfReport);
            if(!xcdr1){
                if (dheaderPosition != -1) {
                    dst.setDHeader(dheaderPosition);
                }

            }
        }

        if (serialize_encapsulation) {
            dst.restoreAlignment(position);
        }
    }

    public long serialize_to_cdr_buffer(
        byte[] buffer,
        long length,
        DdsDatacenterSimple src)
    {
        return super.serialize_to_cdr_buffer(buffer,length,src);
    }

    public long serialize_to_cdr_buffer(
        byte[] buffer,
        long length,
        DdsDatacenterSimple src,
        short representation)
    {
        return super.serialize_to_cdr_buffer(
            buffer,
            length,
            src,
            representation);
    }
    @Override
    public void serialize_key(
        java.lang.Object endpoint_data,
        java.lang.Object src,
        CdrOutputStream dst,
        boolean serialize_encapsulation,
        short final_encapsulation_id,
        boolean serialize_key,
        java.lang.Object endpoint_plugin_qos)
    {
        int position = 0;
        long memberId = 0;
        int memberLengthPosition = 0;
        int dheaderPosition = -1;
        boolean inBaseClass_tmp = false;
        inBaseClass_tmp =  dst.inBaseClass;
        dst.inBaseClass = false;
        if (serialize_encapsulation) {
            dst.serializeAndSetCdrEncapsulation(final_encapsulation_id, ExtensibilityKind.EXTENSIBLE_EXTENSIBILITY);

            position = dst.resetAlignment();
        } else {
            dst.setEncapsulationKind(final_encapsulation_id);
        }

        if (serialize_key) {
            boolean xcdr1 = (final_encapsulation_id <= CdrEncapsulation.CDR_ENCAPSULATION_ID_PL_CDR_LE)? true: false;

            if (!inBaseClass_tmp && !xcdr1) {
                dheaderPosition=dst.writeDHeader();
            }
            DdsDatacenterSimple typedSrc = (DdsDatacenterSimple) src;
            dst.inBaseClass = false;

            boolean needExtendedId;
            dst.writeInt(typedSrc.id);

            if(!xcdr1){
                if (dheaderPosition != -1) {
                    dst.setDHeader(dheaderPosition);
                }

            }
        }

        if (serialize_encapsulation) {
            dst.restoreAlignment(position);
        }
    }

    @Override
    public void serialize_key_for_keyhash(
        java.lang.Object endpoint_data,
        java.lang.Object src,
        CdrOutputStream dst,
        boolean serialize_encapsulation,
        short final_encapsulation_id,
        boolean serialize_key,
        java.lang.Object endpoint_plugin_qos)
    {
        int position = 0;
        CdrPrimitiveType _cdrPrimitiveType = CdrPrimitiveType.getInstance(final_encapsulation_id);
        short encapsulation_id = CdrEncapsulation.getEncapsulationFromFinal(
            final_encapsulation_id,
            ExtensibilityKind.EXTENSIBLE_EXTENSIBILITY);
        boolean xcdr1 = (encapsulation_id <= CdrEncapsulation.CDR_ENCAPSULATION_ID_PL_CDR_LE);
        if (xcdr1){
            serialize_key (
                endpoint_data,
                src,
                dst,
                serialize_encapsulation,
                final_encapsulation_id,
                serialize_key,
                endpoint_plugin_qos);

        } else {

            if (serialize_encapsulation) {
                dst.serializeAndSetCdrEncapsulation(encapsulation_id);

                position = dst.resetAlignment();
            } else {
                /* We do this to prepare the stream to serialize using xcdr2 if needed
                * as in md5Stream we pass serialize_encapsulation ton false.
                */
                dst.setEncapsulationKind(final_encapsulation_id);
            }

            if (serialize_key) {
                DdsDatacenterSimple typedSrc = (DdsDatacenterSimple) src;
                dst.inBaseClass = false;
                dst.writeInt(typedSrc.id);
            }

            if (serialize_encapsulation) {
                dst.restoreAlignment(position);
            }
        }
    }

    @Override
    public java.lang.Object deserialize_sample(
        java.lang.Object endpoint_data,
        java.lang.Object dst,
        CdrInputStream src, boolean deserialize_encapsulation,
        boolean deserialize_sample,
        java.lang.Object endpoint_plugin_qos)
    {
        int position = 0;
        int tmpPosition = 0, tmpSize = 0;
        long tmpLength = 0;
        CdrBuffer buffer = null;
        boolean inBaseClass_tmp = false;
        inBaseClass_tmp =  src.inBaseClass;
        src.inBaseClass = false;

        if(deserialize_encapsulation) {
            src.deserializeAndSetCdrEncapsulation();

            position = src.resetAlignment();
        }

        if(deserialize_sample) {

            short encapsulation_id = src.getEncapsulationKind();
            boolean xcdr1 = (encapsulation_id <= CdrEncapsulation.CDR_ENCAPSULATION_ID_PL_CDR_LE)? true: false;
            if(!xcdr1){
                buffer = src.getBuffer();
            }
            DdsDatacenterSimple typedDst = (DdsDatacenterSimple) dst;
            typedDst.clear();
            int DHtmpPosition = 0;
            int DHtmpSize = 0;
            long DHtmpLength = 0;
            if (!xcdr1 && !inBaseClass_tmp) {
                DHtmpLength = src.readInt();
                DHtmpPosition = buffer.currentPosition();
                DHtmpSize = buffer.getDesBufferSize();
                buffer.setDesBufferSize((int)(DHtmpPosition + DHtmpLength));
            }

            try{
                typedDst.id = src.readInt();
                typedDst.timestampOfReport = src.readInt();

            } catch (IllegalCdrStateException stateEx) {
                if (src.available() >= CdrEncapsulation.CDR_ENCAPSULATION_PARAMETER_ID_ALIGNMENT) {
                    throw new RETCODE_ERROR("Error deserializing sample! Remainder: " + src.available() + "\n" +
                    "Exception caused by: " + stateEx.getMessage());
                }
            } catch (java.lang.Exception ex) {
                throw new RETCODE_ERROR(ex.getMessage());
            }
            if (!xcdr1 && !inBaseClass_tmp ) {
                buffer.restore(DHtmpSize, (int) (DHtmpPosition + DHtmpLength));
            }
        }
        if (deserialize_encapsulation) {
            src.restoreAlignment(position);
        }

        return dst;
    }

    public void deserialize_from_cdr_buffer(
        DdsDatacenterSimple dst,
        byte[] buffer,
        long length)
    {
        super.deserialize_from_cdr_buffer(dst,buffer,length);
    }

    public java.lang.String data_to_string(
        DdsDatacenterSimple sample,
        PrintFormatProperty property)
    {
        return super.data_to_string(sample, property);
    }

    public java.lang.String data_to_string(
        DdsDatacenterSimple sample)
    {
        return super.data_to_string(sample);
    }

    @Override
    public java.lang.Object deserialize_key_sample(
        java.lang.Object endpoint_data,
        java.lang.Object dst,
        CdrInputStream src,
        boolean deserialize_encapsulation,
        boolean deserialize_key,
        java.lang.Object endpoint_plugin_qos)
    {
        int position = 0;
        int tmpPosition = 0, tmpSize = 0;
        long tmpLength = 0;
        CdrBuffer buffer = null;

        boolean inBaseClass_tmp = false;
        inBaseClass_tmp =  src.inBaseClass;
        src.inBaseClass = false;
        if(deserialize_encapsulation) {
            src.deserializeAndSetCdrEncapsulation();

            position = src.resetAlignment();
        }

        if(deserialize_key) {
            short encapsulation_id = src.getEncapsulationKind();
            boolean xcdr1 = (encapsulation_id <= CdrEncapsulation.CDR_ENCAPSULATION_ID_PL_CDR_LE)? true: false;
            if(!xcdr1){
                buffer = src.getBuffer();
            }
            int DHtmpPosition = 0;
            int DHtmpSize = 0;
            long DHtmpLength = 0;
            if (!xcdr1 && !inBaseClass_tmp) {
                DHtmpLength = src.readInt();
                DHtmpPosition = buffer.currentPosition();
                DHtmpSize = buffer.getDesBufferSize();
                buffer.setDesBufferSize((int)(DHtmpPosition + DHtmpLength));
            }
            DdsDatacenterSimple typedDst = (DdsDatacenterSimple) dst;

            typedDst.id = src.readInt();
            if (!xcdr1 && !inBaseClass_tmp ) {
                buffer.restore(DHtmpSize, (int) (DHtmpPosition + DHtmpLength));
            }
        }
        if (deserialize_encapsulation) {
            src.restoreAlignment(position);
        }

        return dst;
    }

    @Override
    public void skip(java.lang.Object endpoint_data,
    CdrInputStream src,
    boolean skip_encapsulation,
    boolean skip_sample,
    java.lang.Object endpoint_plugin_qos)
    {
        int position = 0;
        int tmpPosition = 0, tmpSize = 0;
        long tmpLength = 0;
        CdrBuffer buffer = null;

        boolean inBaseClass_tmp = false;
        inBaseClass_tmp =  src.inBaseClass;
        src.inBaseClass = false;
        if (skip_encapsulation) {
            src.skipEncapsulation();

            position = src.resetAlignment();
        }

        if (skip_sample) {
            short encapsulation_id = src.getEncapsulationKind();
            boolean xcdr1 = (encapsulation_id <= CdrEncapsulation.CDR_ENCAPSULATION_ID_PL_CDR_LE)? true: false;
            if(!xcdr1){
                buffer = src.getBuffer();
            }
            int DHtmpPosition = 0;
            long DHtmpLength = 0;
            if (!xcdr1 && !inBaseClass_tmp) {
                DHtmpLength = src.readInt();
                DHtmpPosition = buffer.currentPosition();
                buffer.setCurrentPosition((int) (DHtmpPosition + DHtmpLength));
                if (skip_encapsulation) {
                    src.restoreAlignment(position);
                }
                return;
            }

            try {
                src.skipInt();

                src.skipInt();

            } catch (IllegalCdrStateException stateEx) {
                if (src.available() >=
                CdrEncapsulation.CDR_ENCAPSULATION_PARAMETER_ID_ALIGNMENT) {
                    throw new IllegalCdrStateException(
                        "Error skipping sample! Remainder:" + src.available()
                        + "\nException caused by: " + stateEx.getMessage());
                }
            }
        }

        if (skip_encapsulation) {
            src.restoreAlignment(position);
        }
    }

    @Override
    public java.lang.Object serialized_sample_to_key(
        java.lang.Object endpoint_data,
        java.lang.Object sample,
        CdrInputStream src,
        boolean deserialize_encapsulation,
        boolean deserialize_key,
        java.lang.Object endpoint_plugin_qos)
    {

        int position = 0;
        int tmpPosition = 0, tmpSize = 0;
        long tmpLength = 0;
        CdrBuffer buffer = null;

        boolean inBaseClass_tmp = false;
        inBaseClass_tmp =  src.inBaseClass;
        src.inBaseClass = false;
        if(deserialize_encapsulation) {
            src.deserializeAndSetCdrEncapsulation();

            position = src.resetAlignment();
        }

        if (deserialize_key) {

            short encapsulation_id = src.getEncapsulationKind();
            boolean xcdr1 = (encapsulation_id <= CdrEncapsulation.CDR_ENCAPSULATION_ID_PL_CDR_LE)? true: false;
            if(!xcdr1){
                buffer = src.getBuffer();
            }
            DdsDatacenterSimple typedDst = (DdsDatacenterSimple) sample;
            int DHtmpPosition = 0;
            int DHtmpSize = 0;
            long DHtmpLength = 0;
            if (!xcdr1 && !inBaseClass_tmp) {
                DHtmpLength = src.readInt();
                DHtmpPosition = buffer.currentPosition();
                DHtmpSize = buffer.getDesBufferSize();
                buffer.setDesBufferSize((int)(DHtmpPosition + DHtmpLength));
            }
            try {

                typedDst.id = src.readInt();

                src.skipInt();
            } catch (IllegalCdrStateException stateEx) {
                if (src.available() >=
                CdrEncapsulation.CDR_ENCAPSULATION_PARAMETER_ID_ALIGNMENT) {
                    throw new IllegalCdrStateException(
                        "Error skipping sample! Remainder:" + src.available()
                        + "\nException caused by: " + stateEx.getMessage());
                }
            }
            if (!xcdr1 && !inBaseClass_tmp ) {
                buffer.restore(DHtmpSize, (int) (DHtmpPosition + DHtmpLength));
            }

        }

        if (deserialize_encapsulation) {
            src.restoreAlignment(position);
        }

        return sample;
    }

    /* Fill in the key fields of the given instance sample based on the key.
    */
    public void key_to_instance(java.lang.Object endpoint_data,
    java.lang.Object instance,
    java.lang.Object key) {
        DdsDatacenterSimple typedDst
        = (DdsDatacenterSimple) instance;
        DdsDatacenterSimple typedSrc
        = (DdsDatacenterSimple) key;
        typedDst.id = typedSrc.id;

    }

    /* Fill in the given key based on the key fields of the given instance
    * sample.
    */
    public void instance_to_key(java.lang.Object endpoint_data,
    java.lang.Object key,
    java.lang.Object instance) {
        DdsDatacenterSimple typedDst
        = (DdsDatacenterSimple)key;
        DdsDatacenterSimple typedSrc
        = (DdsDatacenterSimple) instance;
        typedDst.id = typedSrc.id;

    }

    @Override
    public void serialized_sample_to_keyhash(
        java.lang.Object endpoint_data,
        CdrInputStream src,
        KeyHash_t keyhash,
        boolean include_encapsulation,
        java.lang.Object endpoint_plugin_qos)
    {
        int position = 0;
        CdrBuffer buffer = null;
        boolean inBaseClass_tmp = false;
        inBaseClass_tmp =  src.inBaseClass;
        src.inBaseClass = false;

        DefaultEndpointData endpointData = (DefaultEndpointData) endpoint_data;
        java.lang.Object sample = null;

        sample = endpointData.get_sample();

        if (sample == null) {
            throw new RETCODE_ERROR("Missing intermediate sample");
        }

        DdsDatacenterSimple typedDst = (DdsDatacenterSimple) sample;

        if (include_encapsulation) {
            src.deserializeAndSetCdrEncapsulation();

            position = src.resetAlignment();
        }
        short encapsulation_id = src.getEncapsulationKind();
        boolean xcdr1 = (encapsulation_id <= CdrEncapsulation.CDR_ENCAPSULATION_ID_PL_CDR_LE)? true: false;
        if(!xcdr1){
            buffer = src.getBuffer();
        }
        typedDst.clear();
        int DHtmpPosition = 0;
        int DHtmpSize = 0;
        long DHtmpLength = 0;
        if (!xcdr1 && !inBaseClass_tmp) {
            DHtmpLength = src.readInt();
            DHtmpPosition = buffer.currentPosition();
            DHtmpSize = buffer.getDesBufferSize();
            buffer.setDesBufferSize((int)(DHtmpPosition + DHtmpLength));
        }

        typedDst.id = src.readInt();

        if (!xcdr1 && !inBaseClass_tmp ) {
            buffer.restore(DHtmpSize, (int) (DHtmpPosition + DHtmpLength));
        }

        if (include_encapsulation) {
            src.restoreAlignment(position);
        }

        instance_to_keyhash(
            endpoint_data,
            keyhash,
            sample,
            src.getEncapsulationKind());
    }

    // -----------------------------------------------------------------------
    // Callbacks
    // -----------------------------------------------------------------------
    @Override
    public Object on_participant_attached(java.lang.Object registration_data,
    TypeSupportParticipantInfo participant_info,
    boolean top_level_registration,
    java.lang.Object container_plugin_context,
    TypeCode type_code) {
        return super.on_participant_attached(
            registration_data, participant_info, top_level_registration,
            container_plugin_context, type_code);
    }

    @Override
    public void on_participant_detached(java.lang.Object participant_data) {
        super.on_participant_detached(participant_data);
    }

    @Override
    public java.lang.Object on_endpoint_attached(java.lang.Object participantData,
    TypeSupportEndpointInfo endpoint_info,
    boolean top_level_registration,
    java.lang.Object container_plugin_context) {
        return super.on_endpoint_attached(
            participantData,  endpoint_info,
            top_level_registration, container_plugin_context);
    }

    @Override
    public void on_endpoint_detached(java.lang.Object endpoint_data) {
        super.on_endpoint_detached(endpoint_data);
    }
    // -----------------------------------------------------------------------
    // Protected Methods
    // -----------------------------------------------------------------------
    @Override
    protected DataWriter create_datawriter(long native_writer,
    DataWriterListener listener,
    int mask) {
        return new DatacenterSimpleDataWriter(native_writer, listener, mask, this);
    }

    @Override
    protected DataReader create_datareader(long native_reader,
    DataReaderListener listener,
    int mask) {

        return new DatacenterSimpleDataReader(native_reader, listener, mask, this);

    }

    // -----------------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------------

    protected DatacenterSimpleTypeSupport() {

        /* If the user data type supports keys, then the second argument
        to the constructor below should be true.  Otherwise it should
        be false. */

        super(TYPE_NAME,true, DatacenterSimpleTypeCode.VALUE, DdsDatacenterSimple.class,TypeSupportType.TST_STRUCT, PLUGIN_VERSION);

    }

    protected DatacenterSimpleTypeSupport(boolean enableKeySupport) {

        super(TYPE_NAME, enableKeySupport, DatacenterSimpleTypeCode.VALUE, DdsDatacenterSimple.class,TypeSupportType.TST_STRUCT, PLUGIN_VERSION);
    }
}

