/*
 * The Alluxio Open Foundation licenses this work under the Apache License, version 2.0
 * (the "License"). You may not use this work except in compliance with the License, which is
 * available at www.apache.org/licenses/LICENSE-2.0
 *
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied, as more fully set forth in the License.
 *
 * See the NOTICE file distributed with this work for information regarding copyright ownership.
 */

package alluxio.client.file.options;

import alluxio.annotation.PublicApi;
import alluxio.thrift.ListStatusTOptions;
import alluxio.wire.LoadMetadataType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.google.common.base.Objects;

import javax.annotation.concurrent.NotThreadSafe;

/**
 * Method options for listing the status.
 */
@PublicApi
@NotThreadSafe
@JsonInclude(Include.NON_EMPTY)
public final class ListStatusOptions {
  private LoadMetadataType mLoadMetadataType;
  private boolean mRecursive;
  private boolean mForceLoadMetaData;
  private boolean mDirAsFile;
  private boolean mPinned;

  /**
   * @return the default {@link ListStatusOptions}
   */
  public static ListStatusOptions defaults() {
    return new ListStatusOptions();
  }

  private ListStatusOptions() {
    mLoadMetadataType = LoadMetadataType.Once;
    mRecursive = false;
    mForceLoadMetaData = false;
    mDirAsFile = false;
    mPinned = false;
  }

  /**
   * @return the load metadata type. It specifies whether the direct children should
   *         be loaded from UFS in different scenarios.
   */
  public LoadMetadataType getLoadMetadataType() {
    return mLoadMetadataType;
  }

  /**
   * @param loadMetadataType the loadMetadataType
   * @return the updated options
   */
  public ListStatusOptions setLoadMetadataType(LoadMetadataType loadMetadataType) {
    mLoadMetadataType = loadMetadataType;
    return this;
  }

  /**
   * @return the recursive. It specifies whether the path has to be traversed recursively
   */
  public boolean ismRecursive() {
    return mRecursive;
  }

  /**
   * @param recursive Recursive option. It specifies whether to display directories recursively
   */
  public void setmRecursive(boolean recursive) {
    mRecursive = recursive;
  }

  /**
   * @return Force Load Metadata Option. It specifies whether to force loading files in the
   *         directory.
   */
  public boolean ismForceLoadMetaData() {
    return mForceLoadMetaData;
  }

  /**
   * @param forceLoadMetaData Option to force loading files in the directory
   */
  public void setmForceLoadMetaData(boolean forceLoadMetaData) {
    mForceLoadMetaData = forceLoadMetaData;
  }

  /**
   * @return Directory As File Option. It specifies whether to list directories as plain files
   */
  public boolean ismDirAsFile() {
    return mDirAsFile;
  }

  /**
   * @param dirAsFile Option to list directories as plain files
   */
  public void setmDirAsFile(boolean dirAsFile) {
    mDirAsFile = dirAsFile;
  }

  /**
   * @return IsPinned Option. It specifies whether to list all the pinned files
   */
  public boolean ismPinned() {
    return mPinned;
  }

  /**
   * @param pinned Option to list all the pinned files
   */
  public void setmPinned(boolean pinned) {
    mPinned = pinned;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ListStatusOptions)) {
      return false;
    }
    ListStatusOptions that = (ListStatusOptions) o;
    return Objects.equal(mLoadMetadataType, that.mLoadMetadataType)
        && Objects.equal(mRecursive, that.mRecursive)
        && Objects.equal(mForceLoadMetaData, that.mForceLoadMetaData)
        && Objects.equal(mDirAsFile, that.mDirAsFile)
        && Objects.equal(mPinned, that.mPinned);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(mLoadMetadataType, mRecursive, mForceLoadMetaData, mDirAsFile, mPinned);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
        .add("loadMetadataType", mLoadMetadataType.toString())
        .toString();
  }

  /**
   * @return thrift representation of the options
   */
  public ListStatusTOptions toThrift() {
    ListStatusTOptions options = new ListStatusTOptions();
    options.setLoadDirectChildren(
        mLoadMetadataType == LoadMetadataType.Once || mLoadMetadataType == LoadMetadataType.Always);

    options.setLoadMetadataType(LoadMetadataType.toThrift(mLoadMetadataType));
    return options;
  }
}
