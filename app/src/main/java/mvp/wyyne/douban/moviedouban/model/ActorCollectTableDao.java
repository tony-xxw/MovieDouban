package mvp.wyyne.douban.moviedouban.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import mvp.wyyne.douban.moviedouban.api.model.ActorCollectTable;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ACTOR_COLLECT_TABLE".
*/
public class ActorCollectTableDao extends AbstractDao<ActorCollectTable, Long> {

    public static final String TABLENAME = "ACTOR_COLLECT_TABLE";

    /**
     * Properties of entity ActorCollectTable.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property AvatarUrl = new Property(1, String.class, "avatarUrl", false, "AVATAR_URL");
        public final static Property ActorName = new Property(2, String.class, "actorName", false, "ACTOR_NAME");
        public final static Property Representative = new Property(3, String.class, "representative", false, "REPRESENTATIVE");
    }


    public ActorCollectTableDao(DaoConfig config) {
        super(config);
    }
    
    public ActorCollectTableDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ACTOR_COLLECT_TABLE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"AVATAR_URL\" TEXT," + // 1: avatarUrl
                "\"ACTOR_NAME\" TEXT," + // 2: actorName
                "\"REPRESENTATIVE\" TEXT);"); // 3: representative
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ACTOR_COLLECT_TABLE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ActorCollectTable entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String avatarUrl = entity.getAvatarUrl();
        if (avatarUrl != null) {
            stmt.bindString(2, avatarUrl);
        }
 
        String actorName = entity.getActorName();
        if (actorName != null) {
            stmt.bindString(3, actorName);
        }
 
        String representative = entity.getRepresentative();
        if (representative != null) {
            stmt.bindString(4, representative);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ActorCollectTable entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String avatarUrl = entity.getAvatarUrl();
        if (avatarUrl != null) {
            stmt.bindString(2, avatarUrl);
        }
 
        String actorName = entity.getActorName();
        if (actorName != null) {
            stmt.bindString(3, actorName);
        }
 
        String representative = entity.getRepresentative();
        if (representative != null) {
            stmt.bindString(4, representative);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public ActorCollectTable readEntity(Cursor cursor, int offset) {
        ActorCollectTable entity = new ActorCollectTable( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // avatarUrl
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // actorName
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3) // representative
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ActorCollectTable entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setAvatarUrl(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setActorName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setRepresentative(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(ActorCollectTable entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(ActorCollectTable entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(ActorCollectTable entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}