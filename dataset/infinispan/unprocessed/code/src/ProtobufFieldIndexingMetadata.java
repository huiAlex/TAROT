package org.infinispan.query.remote.impl;

import java.util.function.BiFunction;

import org.infinispan.objectfilter.impl.syntax.IndexedFieldProvider;
import org.infinispan.protostream.descriptors.Descriptor;
import org.infinispan.protostream.descriptors.FieldDescriptor;
import org.infinispan.protostream.descriptors.JavaType;
import org.infinispan.query.remote.impl.indexing.IndexingMetadata;

/**
 * Tests if a field is indexed by examining the Protobuf metadata.
 *
 * @author anistor@redhat.com
 * @since 8.0
 */
final class ProtobufFieldIndexingMetadata implements IndexedFieldProvider.FieldIndexingMetadata {

   private final Descriptor messageDescriptor;

   ProtobufFieldIndexingMetadata(Descriptor messageDescriptor) {
      if (messageDescriptor == null) {
         throw new IllegalArgumentException("argument cannot be null");
      }
      this.messageDescriptor = messageDescriptor;
   }

   @Override
   public boolean isIndexed(String[] propertyPath) {
      return getMetadata(propertyPath, IndexingMetadata::isFieldIndexed, true);
   }

   @Override
   public boolean isAnalyzed(String[] propertyPath) {
      return getMetadata(propertyPath, IndexingMetadata::isFieldAnalyzed, false);
   }

   @Override
   public boolean isStored(String[] propertyPath) {
      return getMetadata(propertyPath, IndexingMetadata::isFieldStored, true);
   }

   private boolean getMetadata(String[] propertyPath, BiFunction<IndexingMetadata, String, Boolean> metadataFun, boolean defVal) {
      Descriptor md = messageDescriptor;
      int i = 0;
      for (String p : propertyPath) {
         i++;
         FieldDescriptor field = md.findFieldByName(p);
         if (field == null) {
            break;
         }
         IndexingMetadata indexingMetadata = md.getProcessedAnnotation(IndexingMetadata.INDEXED_ANNOTATION);
         boolean res = indexingMetadata == null ? defVal : metadataFun.apply(indexingMetadata, field.getName());
         if (!res) {
            break;
         }
         if (field.getJavaType() == JavaType.MESSAGE) {
            md = field.getMessageType();
         } else {
            return i == propertyPath.length;
         }
      }
      return false;
   }
}
