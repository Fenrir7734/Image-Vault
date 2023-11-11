/*
 * This file is generated by jOOQ.
 */
package com.fenrir.core.database;


import com.fenrir.core.database.tables.AlbumVisibility;
import com.fenrir.core.database.tables.Albums;
import com.fenrir.core.database.tables.Roles;
import com.fenrir.core.database.tables.Users;

import java.util.Arrays;
import java.util.List;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Public extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public</code>
     */
    public static final Public PUBLIC = new Public();

    /**
     * The table <code>public.album_visibility</code>.
     */
    public final AlbumVisibility ALBUM_VISIBILITY = AlbumVisibility.ALBUM_VISIBILITY;

    /**
     * The table <code>public.albums</code>.
     */
    public final Albums ALBUMS = Albums.ALBUMS;

    /**
     * The table <code>public.roles</code>.
     */
    public final Roles ROLES = Roles.ROLES;

    /**
     * The table <code>public.users</code>.
     */
    public final Users USERS = Users.USERS;

    /**
     * No further instances allowed
     */
    private Public() {
        super("public", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.asList(
            AlbumVisibility.ALBUM_VISIBILITY,
            Albums.ALBUMS,
            Roles.ROLES,
            Users.USERS
        );
    }
}
