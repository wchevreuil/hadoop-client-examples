package com.cloudera.support.hbase.scan;

import com.google.common.collect.ImmutableList;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CoprocessorEnvironment;
import org.apache.hadoop.hbase.HRegionInfo;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.coprocessor.ObserverContext;
import org.apache.hadoop.hbase.coprocessor.RegionCoprocessorEnvironment;
import org.apache.hadoop.hbase.coprocessor.RegionObserver;
import org.apache.hadoop.hbase.filter.ByteArrayComparable;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.io.FSDataInputStreamWrapper;
import org.apache.hadoop.hbase.io.Reference;
import org.apache.hadoop.hbase.io.hfile.CacheConfig;
import org.apache.hadoop.hbase.regionserver.*;
import org.apache.hadoop.hbase.regionserver.compactions.CompactionRequest;
import org.apache.hadoop.hbase.regionserver.wal.HLogKey;
import org.apache.hadoop.hbase.regionserver.wal.WALEdit;
import org.apache.hadoop.hbase.util.Pair;
import org.apache.hadoop.hbase.wal.WALKey;

import java.io.IOException;
import java.util.List;
import java.util.NavigableSet;

/**
 * Created by wchevreuil on 29/11/2017.
 */
public class TableAudit implements RegionObserver {

  @Override public void preOpen(
      ObserverContext<RegionCoprocessorEnvironment> observerContext)
      throws IOException {

  }

  @Override public void postOpen(
      ObserverContext<RegionCoprocessorEnvironment> observerContext) {

  }

  @Override public void postLogReplay(
      ObserverContext<RegionCoprocessorEnvironment> observerContext) {

  }

  @Override public InternalScanner preFlushScannerOpen(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      Store store, KeyValueScanner keyValueScanner,
      InternalScanner internalScanner) throws IOException {
    return null;
  }

  @Override public void preFlush(
      ObserverContext<RegionCoprocessorEnvironment> observerContext)
      throws IOException {

  }

  @Override public InternalScanner preFlush(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      Store store, InternalScanner internalScanner) throws IOException {
    return null;
  }

  @Override public void postFlush(
      ObserverContext<RegionCoprocessorEnvironment> observerContext)
      throws IOException {

  }

  @Override public void postFlush(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      Store store, StoreFile storeFile) throws IOException {

  }

  @Override public void preCompactSelection(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      Store store, List<StoreFile> list, CompactionRequest compactionRequest)
      throws IOException {

  }

  @Override public void preCompactSelection(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      Store store, List<StoreFile> list) throws IOException {

  }

  @Override public void postCompactSelection(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      Store store, ImmutableList<StoreFile> immutableList,
      CompactionRequest compactionRequest) {

  }

  @Override public void postCompactSelection(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      Store store, ImmutableList<StoreFile> immutableList) {

  }

  @Override public InternalScanner preCompact(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      Store store, InternalScanner internalScanner, ScanType scanType,
      CompactionRequest compactionRequest) throws IOException {
    return null;
  }

  @Override public InternalScanner preCompact(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      Store store, InternalScanner internalScanner, ScanType scanType)
      throws IOException {
    return null;
  }

  @Override public InternalScanner preCompactScannerOpen(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      Store store, List<? extends KeyValueScanner> list, ScanType scanType,
      long l, InternalScanner internalScanner,
      CompactionRequest compactionRequest) throws IOException {
    return null;
  }

  @Override public InternalScanner preCompactScannerOpen(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      Store store, List<? extends KeyValueScanner> list, ScanType scanType,
      long l, InternalScanner internalScanner) throws IOException {
    return null;
  }

  @Override public void postCompact(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      Store store, StoreFile storeFile, CompactionRequest compactionRequest)
      throws IOException {

  }

  @Override public void postCompact(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      Store store, StoreFile storeFile) throws IOException {

  }

  @Override public void preSplit(
      ObserverContext<RegionCoprocessorEnvironment> observerContext)
      throws IOException {

  }

  @Override public void preSplit(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      byte[] bytes) throws IOException {

  }

  @Override public void postSplit(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      Region region, Region region1) throws IOException {

  }

  @Override public void preSplitBeforePONR(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      byte[] bytes, List<Mutation> list) throws IOException {

  }

  @Override public void preSplitAfterPONR(
      ObserverContext<RegionCoprocessorEnvironment> observerContext)
      throws IOException {

  }

  @Override public void preRollBackSplit(
      ObserverContext<RegionCoprocessorEnvironment> observerContext)
      throws IOException {

  }

  @Override public void postRollBackSplit(
      ObserverContext<RegionCoprocessorEnvironment> observerContext)
      throws IOException {

  }

  @Override public void postCompleteSplit(
      ObserverContext<RegionCoprocessorEnvironment> observerContext)
      throws IOException {

  }

  @Override public void preClose(
      ObserverContext<RegionCoprocessorEnvironment> observerContext, boolean b)
      throws IOException {

  }

  @Override public void postClose(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      boolean b) {

  }

  @Override public void preGetClosestRowBefore(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      byte[] bytes, byte[] bytes1, Result result) throws IOException {

  }

  @Override public void postGetClosestRowBefore(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      byte[] bytes, byte[] bytes1, Result result) throws IOException {

  }

  @Override public void preGetOp(
      ObserverContext<RegionCoprocessorEnvironment> observerContext, Get get,
      List<Cell> list) throws IOException {

  }

  @Override public void postGetOp(
      ObserverContext<RegionCoprocessorEnvironment> observerContext, Get get,
      List<Cell> list) throws IOException {

  }

  @Override public boolean preExists(
      ObserverContext<RegionCoprocessorEnvironment> observerContext, Get get,
      boolean b) throws IOException {
    return false;
  }

  @Override public boolean postExists(
      ObserverContext<RegionCoprocessorEnvironment> observerContext, Get get,
      boolean b) throws IOException {
    return false;
  }

  @Override public void prePut(
      ObserverContext<RegionCoprocessorEnvironment> observerContext, Put put,
      WALEdit walEdit, Durability durability) throws IOException {

  }

  @Override public void postPut(
      ObserverContext<RegionCoprocessorEnvironment> observerContext, Put put,
      WALEdit walEdit, Durability durability) throws IOException {

  }

  @Override public void preDelete(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      Delete delete, WALEdit walEdit, Durability durability)
      throws IOException {

  }

  @Override public void prePrepareTimeStampForDeleteVersion(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      Mutation mutation, Cell cell, byte[] bytes, Get get) throws IOException {

  }

  @Override public void postDelete(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      Delete delete, WALEdit walEdit, Durability durability)
      throws IOException {

  }

  @Override public void preBatchMutate(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      MiniBatchOperationInProgress<Mutation> miniBatchOperationInProgress)
      throws IOException {

  }

  @Override public void postBatchMutate(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      MiniBatchOperationInProgress<Mutation> miniBatchOperationInProgress)
      throws IOException {

  }

  @Override public void postStartRegionOperation(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      Region.Operation operation) throws IOException {

  }

  @Override public void postCloseRegionOperation(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      Region.Operation operation) throws IOException {

  }

  @Override public void postBatchMutateIndispensably(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      MiniBatchOperationInProgress<Mutation> miniBatchOperationInProgress,
      boolean b) throws IOException {

  }

  @Override public boolean preCheckAndPut(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      byte[] bytes, byte[] bytes1, byte[] bytes2,
      CompareFilter.CompareOp compareOp,
      ByteArrayComparable byteArrayComparable, Put put, boolean b)
      throws IOException {
    return false;
  }

  @Override public boolean preCheckAndPutAfterRowLock(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      byte[] bytes, byte[] bytes1, byte[] bytes2,
      CompareFilter.CompareOp compareOp,
      ByteArrayComparable byteArrayComparable, Put put, boolean b)
      throws IOException {
    return false;
  }

  @Override public boolean postCheckAndPut(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      byte[] bytes, byte[] bytes1, byte[] bytes2,
      CompareFilter.CompareOp compareOp,
      ByteArrayComparable byteArrayComparable, Put put, boolean b)
      throws IOException {
    return false;
  }

  @Override public boolean preCheckAndDelete(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      byte[] bytes, byte[] bytes1, byte[] bytes2,
      CompareFilter.CompareOp compareOp,
      ByteArrayComparable byteArrayComparable, Delete delete, boolean b)
      throws IOException {
    return false;
  }

  @Override public boolean preCheckAndDeleteAfterRowLock(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      byte[] bytes, byte[] bytes1, byte[] bytes2,
      CompareFilter.CompareOp compareOp,
      ByteArrayComparable byteArrayComparable, Delete delete, boolean b)
      throws IOException {
    return false;
  }

  @Override public boolean postCheckAndDelete(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      byte[] bytes, byte[] bytes1, byte[] bytes2,
      CompareFilter.CompareOp compareOp,
      ByteArrayComparable byteArrayComparable, Delete delete, boolean b)
      throws IOException {
    return false;
  }

  @Override public long preIncrementColumnValue(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      byte[] bytes, byte[] bytes1, byte[] bytes2, long l, boolean b)
      throws IOException {
    return 0;
  }

  @Override public long postIncrementColumnValue(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      byte[] bytes, byte[] bytes1, byte[] bytes2, long l, boolean b, long l1)
      throws IOException {
    return 0;
  }

  @Override public Result preAppend(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      Append append) throws IOException {
    return null;
  }

  @Override public Result preAppendAfterRowLock(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      Append append) throws IOException {
    return null;
  }

  @Override public Result postAppend(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      Append append, Result result) throws IOException {
    return null;
  }

  @Override public Result preIncrement(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      Increment increment) throws IOException {
    return null;
  }

  @Override public Result preIncrementAfterRowLock(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      Increment increment) throws IOException {
    return null;
  }

  @Override public Result postIncrement(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      Increment increment, Result result) throws IOException {
    return null;
  }

  @Override public RegionScanner preScannerOpen(
      ObserverContext<RegionCoprocessorEnvironment> observerContext, Scan scan,
      RegionScanner regionScanner) throws IOException {



    return null;
  }

  @Override public KeyValueScanner preStoreScannerOpen(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      Store store, Scan scan, NavigableSet<byte[]> navigableSet,
      KeyValueScanner keyValueScanner) throws IOException {
    return null;
  }

  @Override public RegionScanner postScannerOpen(
      ObserverContext<RegionCoprocessorEnvironment> observerContext, Scan scan,
      RegionScanner regionScanner) throws IOException {
    return null;
  }

  @Override public boolean preScannerNext(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      InternalScanner internalScanner, List<Result> list, int i, boolean b)
      throws IOException {
    return false;
  }

  @Override public boolean postScannerNext(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      InternalScanner internalScanner, List<Result> list, int i, boolean b)
      throws IOException {
    return false;
  }

  @Override public boolean postScannerFilterRow(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      InternalScanner internalScanner, byte[] bytes, int i, short i1, boolean b)
      throws IOException {
    return false;
  }

  @Override public void preScannerClose(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      InternalScanner internalScanner) throws IOException {

  }

  @Override public void postScannerClose(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      InternalScanner internalScanner) throws IOException {

  }

  @Override public void preWALRestore(
      ObserverContext<? extends RegionCoprocessorEnvironment> observerContext,
      HRegionInfo hRegionInfo, WALKey walKey, WALEdit walEdit)
      throws IOException {

  }

  @Override public void preWALRestore(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      HRegionInfo hRegionInfo, HLogKey hLogKey, WALEdit walEdit)
      throws IOException {

  }

  @Override public void postWALRestore(
      ObserverContext<? extends RegionCoprocessorEnvironment> observerContext,
      HRegionInfo hRegionInfo, WALKey walKey, WALEdit walEdit)
      throws IOException {

  }

  @Override public void postWALRestore(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      HRegionInfo hRegionInfo, HLogKey hLogKey, WALEdit walEdit)
      throws IOException {

  }

  @Override public void preBulkLoadHFile(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      List<Pair<byte[], String>> list) throws IOException {

  }

  @Override public boolean postBulkLoadHFile(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      List<Pair<byte[], String>> list, boolean b) throws IOException {
    return false;
  }

  @Override public StoreFile.Reader preStoreFileReaderOpen(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      FileSystem fileSystem, Path path,
      FSDataInputStreamWrapper fsDataInputStreamWrapper, long l,
      CacheConfig cacheConfig, Reference reference, StoreFile.Reader reader)
      throws IOException {
    return null;
  }

  @Override public StoreFile.Reader postStoreFileReaderOpen(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      FileSystem fileSystem, Path path,
      FSDataInputStreamWrapper fsDataInputStreamWrapper, long l,
      CacheConfig cacheConfig, Reference reference, StoreFile.Reader reader)
      throws IOException {
    return null;
  }

  @Override public Cell postMutationBeforeWAL(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      MutationType mutationType, Mutation mutation, Cell cell,
      Cell cell1) throws IOException {
    return null;
  }

  @Override public DeleteTracker postInstantiateDeleteTracker(
      ObserverContext<RegionCoprocessorEnvironment> observerContext,
      DeleteTracker deleteTracker) throws IOException {
    return null;
  }

  @Override public void start(CoprocessorEnvironment coprocessorEnvironment)
      throws IOException {

  }

  @Override public void stop(CoprocessorEnvironment coprocessorEnvironment)
      throws IOException {

  }
}
