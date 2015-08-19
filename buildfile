repositories.local = 'D:\Users\Tadhg Dev\Applications\Buildr\Repository'

####################################################################
# Setup - Properties
####################################################################

  puts 'Setup - Properties...'
  
  AppName						= 'oDir'
  AppVersion					= '01.04.02'
  Year							= '2013'
  
  oAppName						= "oApp"
  oDirName						= "oDir"
  oDocName						= "oDoc"
  oWebName						= "oWeb"
  
  AppMainDir					=  ENV['Dev.Projects'] + '/' + AppName
  AppVersionDir					=  AppMainDir + 'Source/Java'

  SourceDir						= "Source"
  ResourcesDir					= "Resources"
  BuildDir						= "Build"
  ReleaseDir					= "Release"
  BuildScripts					= "Build Scripts"
  BackupDir						=  AppVersionDir + '/v ' + AppVersion

  SourceApp						=  SourceDir + '/App'
  SourceAppStart				=  SourceDir + '/App.Start'
  SourceCore					=  SourceDir + '/Core'
  SourceShared					=  SourceDir + '/Shared'

  manifest_hash = {
  
	'Build-By'					=> 'AppWorks',
	'Copyright'					=> 'AppWorks - ' + Year,
  	'Implementation-Title:'		=>  AppName,
  	'Implementation-Version:'	=>  AppVersion,
	'Main-Class'				=> 'Start'}
  


####################################################################
# Build - Setup
####################################################################

  puts 'Projects...'

  define 'odir' do
  
    project.version = AppVersion
    
    compile.from (SourceApp,SourceCore,SourceShared)
    
    package :type=>:jar, :file=>_(BuildDir + "/" + oDirName + ".jar")
    package(:jar).with :manifest=>manifest_hash
   
    
    task :clean do
    
      puts oAppName + ' - Cleaning...'
      
    end
  
  
    task :compile do
    
      puts oAppName + ' - Compiling...'
      
    end
  
  
    task :build do
    
      puts oAppName + ' - Building...'
      
    end
  
  
    task :package do
    
      puts oAppName + ' - Packaging...'
      
    end
    
    
    task :Backup do
    
      puts oAppName + ' - Backup...'
      
      FileUtils.rm_rf	BackupDir
      FileUtils.mkdir	BackupDir
      
      FileUtils.cp_r	SourceDir,  BackupDir + '/' + SourceDir
      FileUtils.cp_r	ReleaseDir, BackupDir + '/' + ReleaseDir
      
      FileUtils.mkdir	BackupDir + '/' + BuildScripts
      FileUtils.cp_r	'buildfile', BackupDir + '/' + BuildScripts
      
    end
      
  end


####################################################################
# Done.
####################################################################

  puts 'Done.'
