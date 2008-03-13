/*
 * Reserve.java
 *
 * Created on July 20, 2006, 8:56 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package diskCacheV111.services.space.message;
import diskCacheV111.vehicles.Message;
import diskCacheV111.util.RetentionPolicy;
import diskCacheV111.util.AccessLatency;
import diskCacheV111.util.PnfsId;

/**
 *
 * @author timur
 */
public class Use extends Message{
    static final long serialVersionUID = 7864026870745603985L;
    private long spaceToken;
    private String voGroup;
    private String voRole;
    private String pnfsName;
    private PnfsId pnfsId;
    private long sizeInBytes;
    private RetentionPolicy retentionPolicy;
    private AccessLatency accessLatency;
    private long lifetime; //this is the lifetime of this file reservation 
                           // not file lifetime after it is written
                           // this is how long user has to write the file
    private long fileId;
    /** Creates a new instance of Reserve */
    public Use() {
    }
    
    public Use(
            long spaceToken,
            String voGroup, 
            String voRole, 
            String pnfsName,
            PnfsId pnfsId,
            long sizeInBytes,
            long lifetime){
        this.spaceToken = spaceToken;
        this.voGroup = voGroup;
        this.voRole = voRole;
        this.sizeInBytes = sizeInBytes;
        this.pnfsName= pnfsName;
        this.pnfsId = pnfsId;
        this.lifetime = lifetime;
        setReplyRequired(true);
    }

    public long getSpaceToken() {
        return spaceToken;
    }

    public void setSpaceToken(long spaceToken) {
        this.spaceToken = spaceToken;
    }


    public String getPnfsName() {
        return pnfsName;
    }

    public void setPnfsName(String pnfsName) {
        this.pnfsName = pnfsName;
    }

    public PnfsId getPnfsId() {
        return pnfsId;
    }

    public void setPnfsId(PnfsId pnfsId) {
        this.pnfsId = pnfsId;
    }

    public long getSizeInBytes() {
        return sizeInBytes;
    }

    public void setSizeInBytes(long sizeInBytes) {
        this.sizeInBytes = sizeInBytes;
    }

    public RetentionPolicy getRetentionPolicy() {
        return retentionPolicy;
    }

    public void setRetentionPolicy(RetentionPolicy retentionPolicy) {
        this.retentionPolicy = retentionPolicy;
    }

    public AccessLatency getAccessLatency() {
        return accessLatency;
    }

    public void setAccessLatency(AccessLatency accessLatency) {
        this.accessLatency = accessLatency;
    }

    public long getLifetime() {
        return lifetime;
    }

    public void setLifetime(long lifetime) {
        this.lifetime = lifetime;
    }

    public long getFileId() {
        return fileId;
    }

    public void setFileId(long fileId) {
        this.fileId = fileId;
    }

    public String getVoGroup() {
        return voGroup;
    }

    public void setVoGroup(String voGroup) {
        this.voGroup = voGroup;
    }

    public String getVoRole() {
        return voRole;
    }

    public void setVoRole(String voRole) {
        this.voRole = voRole;
    }

}
