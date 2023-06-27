// =================== DO NOT EDIT THIS FILE ====================
// Generated by Modello 1.9.1,
// any modifications will be overwritten.
// ==============================================================

package org.apache.maven.settings;

/**
 * 
 *         Root element of the user configuration file.
 *       
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings( "all" )
public class Settings
    extends TrackableBase
    implements java.io.Serializable, java.lang.Cloneable
{

      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * 
     *             
     *             The local repository.<br><b>Default value
     * is:</b> <tt>${user.home}/.m2/repository</tt>
     *             
     *           
     */
    private String localRepository;

    /**
     * 
     *             
     *             Whether Maven should attempt to interact with
     * the user for input.
     *             
     *           
     */
    private boolean interactiveMode = true;

    /**
     * 
     *             
     *             Whether Maven should use the plugin-registry.xml
     * file to manage plugin versions.
     *             
     *           
     */
    private boolean usePluginRegistry = false;

    /**
     * 
     *             
     *             Indicate whether maven should operate in offline
     * mode full-time.
     *             
     *           
     */
    private boolean offline = false;

    /**
     * Field proxies.
     */
    private java.util.List<Proxy> proxies;

    /**
     * Field servers.
     */
    private java.util.List<Server> servers;

    /**
     * Field mirrors.
     */
    private java.util.List<Mirror> mirrors;

    /**
     * Field profiles.
     */
    private java.util.List<Profile> profiles;

    /**
     * Field activeProfiles.
     */
    private java.util.List<String> activeProfiles;

    /**
     * Field pluginGroups.
     */
    private java.util.List<String> pluginGroups;

    /**
     * Field modelEncoding.
     */
    private String modelEncoding = "UTF-8";


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addActiveProfile.
     * 
     * @param string
     */
    public void addActiveProfile( String string )
    {
        getActiveProfiles().add( string );
    } //-- void addActiveProfile( String )

    /**
     * Method addMirror.
     * 
     * @param mirror
     */
    public void addMirror( Mirror mirror )
    {
        getMirrors().add( mirror );
    } //-- void addMirror( Mirror )

    /**
     * Method addPluginGroup.
     * 
     * @param string
     */
    public void addPluginGroup( String string )
    {
        getPluginGroups().add( string );
    } //-- void addPluginGroup( String )

    /**
     * Method addProfile.
     * 
     * @param profile
     */
    public void addProfile( Profile profile )
    {
        getProfiles().add( profile );
    } //-- void addProfile( Profile )

    /**
     * Method addProxy.
     * 
     * @param proxy
     */
    public void addProxy( Proxy proxy )
    {
        getProxies().add( proxy );
    } //-- void addProxy( Proxy )

    /**
     * Method addServer.
     * 
     * @param server
     */
    public void addServer( Server server )
    {
        getServers().add( server );
    } //-- void addServer( Server )

    /**
     * Method clone.
     * 
     * @return Settings
     */
    public Settings clone()
    {
        try
        {
            Settings copy = (Settings) super.clone();

            if ( this.proxies != null )
            {
                copy.proxies = new java.util.ArrayList<Proxy>();
                for ( Proxy item : this.proxies )
                {
                    copy.proxies.add( ( (Proxy) item).clone() );
                }
            }

            if ( this.servers != null )
            {
                copy.servers = new java.util.ArrayList<Server>();
                for ( Server item : this.servers )
                {
                    copy.servers.add( ( (Server) item).clone() );
                }
            }

            if ( this.mirrors != null )
            {
                copy.mirrors = new java.util.ArrayList<Mirror>();
                for ( Mirror item : this.mirrors )
                {
                    copy.mirrors.add( ( (Mirror) item).clone() );
                }
            }

            if ( this.profiles != null )
            {
                copy.profiles = new java.util.ArrayList<Profile>();
                for ( Profile item : this.profiles )
                {
                    copy.profiles.add( ( (Profile) item).clone() );
                }
            }

            if ( this.activeProfiles != null )
            {
                copy.activeProfiles = new java.util.ArrayList<String>();
                copy.activeProfiles.addAll( this.activeProfiles );
            }

            if ( this.pluginGroups != null )
            {
                copy.pluginGroups = new java.util.ArrayList<String>();
                copy.pluginGroups.addAll( this.pluginGroups );
            }

            return copy;
        }
        catch ( java.lang.Exception ex )
        {
            throw (java.lang.RuntimeException) new java.lang.UnsupportedOperationException( getClass().getName()
                + " does not support clone()" ).initCause( ex );
        }
    } //-- Settings clone()

    /**
     * Method getActiveProfiles.
     * 
     * @return List
     */
    public java.util.List<String> getActiveProfiles()
    {
        if ( this.activeProfiles == null )
        {
            this.activeProfiles = new java.util.ArrayList<String>();
        }

        return this.activeProfiles;
    } //-- java.util.List<String> getActiveProfiles()

    /**
     * Get the local repository.<br><b>Default value is:</b>
     * <tt>${user.home}/.m2/repository</tt>
     * 
     * @return String
     */
    public String getLocalRepository()
    {
        return this.localRepository;
    } //-- String getLocalRepository()

    /**
     * Method getMirrors.
     * 
     * @return List
     */
    public java.util.List<Mirror> getMirrors()
    {
        if ( this.mirrors == null )
        {
            this.mirrors = new java.util.ArrayList<Mirror>();
        }

        return this.mirrors;
    } //-- java.util.List<Mirror> getMirrors()

    /**
     * Get the modelEncoding field.
     * 
     * @return String
     */
    public String getModelEncoding()
    {
        return this.modelEncoding;
    } //-- String getModelEncoding()

    /**
     * Method getPluginGroups.
     * 
     * @return List
     */
    public java.util.List<String> getPluginGroups()
    {
        if ( this.pluginGroups == null )
        {
            this.pluginGroups = new java.util.ArrayList<String>();
        }

        return this.pluginGroups;
    } //-- java.util.List<String> getPluginGroups()

    /**
     * Method getProfiles.
     * 
     * @return List
     */
    public java.util.List<Profile> getProfiles()
    {
        if ( this.profiles == null )
        {
            this.profiles = new java.util.ArrayList<Profile>();
        }

        return this.profiles;
    } //-- java.util.List<Profile> getProfiles()

    /**
     * Method getProxies.
     * 
     * @return List
     */
    public java.util.List<Proxy> getProxies()
    {
        if ( this.proxies == null )
        {
            this.proxies = new java.util.ArrayList<Proxy>();
        }

        return this.proxies;
    } //-- java.util.List<Proxy> getProxies()

    /**
     * Method getServers.
     * 
     * @return List
     */
    public java.util.List<Server> getServers()
    {
        if ( this.servers == null )
        {
            this.servers = new java.util.ArrayList<Server>();
        }

        return this.servers;
    } //-- java.util.List<Server> getServers()

    /**
     * Get whether Maven should attempt to interact with the user
     * for input.
     * 
     * @return boolean
     */
    public boolean isInteractiveMode()
    {
        return this.interactiveMode;
    } //-- boolean isInteractiveMode()

    /**
     * Get indicate whether maven should operate in offline mode
     * full-time.
     * 
     * @return boolean
     */
    public boolean isOffline()
    {
        return this.offline;
    } //-- boolean isOffline()

    /**
     * Get whether Maven should use the plugin-registry.xml file to
     * manage plugin versions.
     * 
     * @return boolean
     */
    public boolean isUsePluginRegistry()
    {
        return this.usePluginRegistry;
    } //-- boolean isUsePluginRegistry()

    /**
     * Method removeActiveProfile.
     * 
     * @param string
     */
    public void removeActiveProfile( String string )
    {
        getActiveProfiles().remove( string );
    } //-- void removeActiveProfile( String )

    /**
     * Method removeMirror.
     * 
     * @param mirror
     */
    public void removeMirror( Mirror mirror )
    {
        getMirrors().remove( mirror );
    } //-- void removeMirror( Mirror )

    /**
     * Method removePluginGroup.
     * 
     * @param string
     */
    public void removePluginGroup( String string )
    {
        getPluginGroups().remove( string );
    } //-- void removePluginGroup( String )

    /**
     * Method removeProfile.
     * 
     * @param profile
     */
    public void removeProfile( Profile profile )
    {
        getProfiles().remove( profile );
    } //-- void removeProfile( Profile )

    /**
     * Method removeProxy.
     * 
     * @param proxy
     */
    public void removeProxy( Proxy proxy )
    {
        getProxies().remove( proxy );
    } //-- void removeProxy( Proxy )

    /**
     * Method removeServer.
     * 
     * @param server
     */
    public void removeServer( Server server )
    {
        getServers().remove( server );
    } //-- void removeServer( Server )

    /**
     * Set list of manually-activated build profiles, specified in
     * the order in which
     *             they should be applied.
     * 
     * @param activeProfiles
     */
    public void setActiveProfiles( java.util.List<String> activeProfiles )
    {
        this.activeProfiles = activeProfiles;
    } //-- void setActiveProfiles( java.util.List )

    /**
     * Set whether Maven should attempt to interact with the user
     * for input.
     * 
     * @param interactiveMode
     */
    public void setInteractiveMode( boolean interactiveMode )
    {
        this.interactiveMode = interactiveMode;
    } //-- void setInteractiveMode( boolean )

    /**
     * Set the local repository.<br><b>Default value is:</b>
     * <tt>${user.home}/.m2/repository</tt>
     * 
     * @param localRepository
     */
    public void setLocalRepository( String localRepository )
    {
        this.localRepository = localRepository;
    } //-- void setLocalRepository( String )

    /**
     * Set configuration of download mirrors for repositories.
     * 
     * @param mirrors
     */
    public void setMirrors( java.util.List<Mirror> mirrors )
    {
        this.mirrors = mirrors;
    } //-- void setMirrors( java.util.List )

    /**
     * Set the modelEncoding field.
     * 
     * @param modelEncoding
     */
    public void setModelEncoding( String modelEncoding )
    {
        this.modelEncoding = modelEncoding;
    } //-- void setModelEncoding( String )

    /**
     * Set indicate whether maven should operate in offline mode
     * full-time.
     * 
     * @param offline
     */
    public void setOffline( boolean offline )
    {
        this.offline = offline;
    } //-- void setOffline( boolean )

    /**
     * Set list of groupIds to search for a plugin when that plugin
     *             groupId is not explicitly provided.
     * 
     * @param pluginGroups
     */
    public void setPluginGroups( java.util.List<String> pluginGroups )
    {
        this.pluginGroups = pluginGroups;
    } //-- void setPluginGroups( java.util.List )

    /**
     * Set configuration of build profiles for adjusting the build
     *             according to environmental parameters.
     * 
     * @param profiles
     */
    public void setProfiles( java.util.List<Profile> profiles )
    {
        this.profiles = profiles;
    } //-- void setProfiles( java.util.List )

    /**
     * Set configuration for different proxy profiles. Multiple
     * proxy profiles
     *             might come in handy for anyone working from a
     * notebook or other
     *             mobile platform, to enable easy switching of
     * entire proxy
     *             configurations by simply specifying the profile
     * id, again either from
     *             the command line or from the defaults section
     * below.
     * 
     * @param proxies
     */
    public void setProxies( java.util.List<Proxy> proxies )
    {
        this.proxies = proxies;
    } //-- void setProxies( java.util.List )

    /**
     * Set configuration of server-specific settings, mainly
     * authentication
     *             method. This allows configuration of
     * authentication on a per-server
     *             basis.
     * 
     * @param servers
     */
    public void setServers( java.util.List<Server> servers )
    {
        this.servers = servers;
    } //-- void setServers( java.util.List )

    /**
     * Set whether Maven should use the plugin-registry.xml file to
     * manage plugin versions.
     * 
     * @param usePluginRegistry
     */
    public void setUsePluginRegistry( boolean usePluginRegistry )
    {
        this.usePluginRegistry = usePluginRegistry;
    } //-- void setUsePluginRegistry( boolean )

    
            
    public Boolean getInteractiveMode()
    {
        return Boolean.valueOf( isInteractiveMode() );
    }

    private Proxy activeProxy;

    /**
     * Reset the <code>activeProxy</code> field to <code>null</code>
     */
    public void flushActiveProxy()
    {
        this.activeProxy = null;
    }

    /**
     * @return the first active proxy
     */
    public synchronized Proxy getActiveProxy()
    {
        if ( activeProxy == null )
        {
            java.util.List<Proxy> proxies = getProxies();
            if ( proxies != null && !proxies.isEmpty() )
            {
                for ( Proxy proxy : proxies )
                {
                    if ( proxy.isActive() )
                    {
                        activeProxy = proxy;
                        break;
                    }
                }
            }
        }

        return activeProxy;
    }

    public Server getServer( String serverId )
    {
        Server match = null;

        java.util.List<Server> servers = getServers();
        if ( servers != null && serverId != null )
        {
            for ( Server server : servers )
            {
                if ( serverId.equals( server.getId() ) )
                {
                    match = server;
                    break;
                }
            }
        }

        return match;
    }

    @Deprecated
    public Mirror getMirrorOf( String repositoryId )
    {
        Mirror match = null;

        java.util.List<Mirror> mirrors = getMirrors();
        if ( mirrors != null && repositoryId != null )
        {
            for ( Mirror mirror : mirrors )
            {
                if ( repositoryId.equals( mirror.getMirrorOf() ) )
                {
                    match = mirror;
                    break;
                }
            }
        }

        return match;
    }

    private java.util.Map<String, Profile> profileMap;

    /**
     * Reset the <code>profileMap</code> field to <code>null</code>
     */
    public void flushProfileMap()
    {
        this.profileMap = null;
    }

    /**
     * @return a Map of profiles field with <code>Profile#getId()</code> as key
     * @see org.apache.maven.settings.Profile#getId()
     */
    public java.util.Map<String, Profile> getProfilesAsMap()
    {
        if ( profileMap == null )
        {
            profileMap = new java.util.LinkedHashMap<String, Profile>();

            if ( getProfiles() != null )
            {
                for ( Profile profile : getProfiles() )
                {
                    profileMap.put( profile.getId(), profile );
                }
            }
        }

        return profileMap;
    }
            
          
}